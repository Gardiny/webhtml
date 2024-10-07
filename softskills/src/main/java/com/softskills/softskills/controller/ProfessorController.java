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

import com.softskills.softskills.controller.dto.ProfessorDto;
import com.softskills.softskills.controller.mapper.ProfessorMapper;
import com.softskills.softskills.model.Professor;
import com.softskills.softskills.service.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/professor",produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController implements IController<ProfessorDto>{

    
    private final ProfessorService servico;
    private final ProfessorMapper mapper;

    public ProfessorController(
        ProfessorService servico,
        ProfessorMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<ProfessorDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC) // Mudar "nome" comforme a variavel em Professor
        }) 
        // @ParameterObject       Adicionar após adicionar springdoc nas dependencias 
        Pageable page) {
        Page<Professor> registros = servico.get(termoBusca, page);
        Page<ProfessorDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> get(@PathVariable("id") Long id) {
        Professor registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ProfessorDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<ProfessorDto> insert(@RequestBody @Valid ProfessorDto objeto) {
        Professor objetoConvertido = mapper.toEntity(objeto);
        Professor registro = servico.save(objetoConvertido);
        ProfessorDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<ProfessorDto> update(@RequestBody @Valid ProfessorDto objeto) {
        Professor objetoCOnvertido = mapper.toEntity(objeto);
        Professor registro = servico.save(objetoCOnvertido);
        ProfessorDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
