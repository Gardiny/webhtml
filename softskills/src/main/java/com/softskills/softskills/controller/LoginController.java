package com.softskills.softskills.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.softskills.softskills.config.PerfilUsuario;
import com.softskills.softskills.config.TokenService;
import com.softskills.softskills.controller.dto.UsuarioDto;
import com.softskills.softskills.controller.mapper.UsuarioMapper;
import com.softskills.softskills.model.Usuario;
import com.softskills.softskills.service.UsuarioService;

@RestController
@RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE)
public class LoginController {
    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioServico;
    private final UsuarioMapper mapper;

    public LoginController(
        AuthenticationManager authManager,
        TokenService tokenService,
        UsuarioService usuarioServico,
        UsuarioMapper mapper) {
            this.authManager = authManager;
            this.tokenService = tokenService;
            this.usuarioServico = usuarioServico;
            this.mapper = mapper;
        }
    
    // basic
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> login() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioServico.getByNomeUsuario(principal.getName());
        UsuarioDto dto = mapper.toDto(usuario);
        return ResponseEntity.ok(dto);
    }

    // jwt
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {

        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(usuario.getNome_usuario(), usuario.getSenha());
        Authentication auth = authManager.authenticate(loginToken);
        PerfilUsuario principal = (PerfilUsuario) auth.getPrincipal();

        Usuario usuarioAutenticado = usuarioServico.getByNomeUsuario(principal.getUsername());
        String token = tokenService.generateToken(usuarioAutenticado);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        DecodedJWT tokenDecodificado = JWT.decode(token);
        Claim claimDataLimite = tokenDecodificado.getClaim("dataLimiteRenovacao");
        LocalDate dataLimite = LocalDate.parse(claimDataLimite.asString());
        LocalDate hoje = LocalDate.now();
        if (hoje.isAfter(dataLimite)) {
            return ResponseEntity.badRequest().build();
        }

        String nomeUsuario = tokenDecodificado.getSubject();
        Usuario usuario = usuarioServico.getByNomeUsuario(nomeUsuario);
        String tokenNovo = tokenService.generateToken(usuario);

        return ResponseEntity.ok(tokenNovo);
    }
}
