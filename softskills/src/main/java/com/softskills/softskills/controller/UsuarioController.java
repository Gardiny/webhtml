package com.softskills.softskills.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softskills.softskills.model.Usuario;
import com.softskills.softskills.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuario",produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements IController<Usuario>{

    
    private final UsuarioService servico;

    public UsuarioController(UsuarioService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<Usuario>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        // @SortDefault.SortDefaults({                                              // precisou ser retirado para o get all não bugar.
        //     @SortDefault(sort = "nome_completo", direction = Sort.Direction.ASC) // Mudar "nome_completo" comforme a variavel em Usuario
        // }) 
        // @ParameterObject       Adicionar após adicionar springdoc nas dependencias 
        Pageable page) {
        Page<Usuario> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> get(@PathVariable("id") Long id) {
        Usuario registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Usuario> insert(@RequestBody @Valid Usuario objeto) {
        Usuario registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Usuario> update(@RequestBody @Valid Usuario objeto) {
        Usuario registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
