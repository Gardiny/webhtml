package com.softskills.softskills.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.softskills.softskills.model.Usuario;

@Service
public class TokenService {
    
    @Value("${jwt.secret}")
    private String secret;

    private Instant generateExpirationDate() {
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(15);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = dateTime.atZone(zoneId);
        return zonedDateTime.toInstant();
    }

    public String generateToken(Usuario usuario) {
        Algorithm alg  = Algorithm.HMAC256(secret);
        String token = JWT.create()
            .withIssuer("SGS")
            .withSubject(usuario.getNome_usuario())
            .withClaim("nome_completo", usuario.getNome_completo())
            .withClaim("papel", usuario.getPapel().name())
            .withClaim("dataLimiteRenovacao", LocalDate.now().toString())
            .withExpiresAt(generateExpirationDate())
            .sign(alg);
        return token;
    }

    public String validateToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        DecodedJWT tokenValidado = JWT.require(alg)
            .withIssuer("SGS")
            .build()
            .verify(token);
        return tokenValidado.getSubject();
    }
}
