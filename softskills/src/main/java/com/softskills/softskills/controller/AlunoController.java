package com.softskills.softskills.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
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

import com.softskills.softskills.model.Aluno;
import com.softskills.softskills.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/aluno",produces = MediaType.APPLICATION_JSON_VALUE)
public class AlunoController implements IController<Aluno> {

    private final AlunoService servico;

    public AlunoController(AlunoService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<Aluno>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC) // Mudar "nome" comforme a variavel em aluno
        }) 
        // @ParameterObject       Adicionar após adicionar springdoc nas dependencias 
        Pageable page) {
        Page<Aluno> registros = servico.get(termoBusca, page);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> get(@PathVariable("id") Long id) {
        Aluno registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Aluno> insert(@RequestBody @Valid Aluno objeto) {
        Aluno registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Aluno> update(@RequestBody @Valid Aluno objeto) {
        Aluno registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
