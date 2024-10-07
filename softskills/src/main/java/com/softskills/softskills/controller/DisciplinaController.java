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

import com.softskills.softskills.controller.dto.DisciplinaDto;
import com.softskills.softskills.controller.mapper.DisciplinaMapper;
import com.softskills.softskills.model.Disciplina;
import com.softskills.softskills.service.DisciplinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/disciplina", produces = MediaType.APPLICATION_JSON_VALUE)
public class DisciplinaController implements IController<DisciplinaDto>{

    
    private final DisciplinaService servico;
    private final DisciplinaMapper mapper;

    public DisciplinaController(
        DisciplinaService servico,
        DisciplinaMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<DisciplinaDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC) // Mudar "nome" comforme a variavel em Diciplina
        }) 
        // @ParameterObject       Adicionar após adicionar springdoc nas dependencias 
        Pageable page) {
        Page<Disciplina> registros = servico.get(termoBusca, page);
        Page<DisciplinaDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDto> get(@PathVariable("id") Long id) {
        Disciplina registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DisciplinaDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<DisciplinaDto> insert(@RequestBody @Valid DisciplinaDto objeto) {
        Disciplina objetoConvertido = mapper.toEntity(objeto);
        Disciplina registro = servico.save(objetoConvertido);
        DisciplinaDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<DisciplinaDto> update(@RequestBody @Valid DisciplinaDto objeto) {
        Disciplina objetoConvertido = mapper.toEntity(objeto);
        Disciplina registro = servico.save(objetoConvertido);
        DisciplinaDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
