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

import com.softskills.softskills.controller.dto.TurmaDto;
import com.softskills.softskills.controller.mapper.TurmaMapper;
import com.softskills.softskills.model.Turma;
import com.softskills.softskills.service.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/turma",produces = MediaType.APPLICATION_JSON_VALUE)
public class TurmaController implements IController<TurmaDto>{
    
    private final TurmaService servico;
    private final TurmaMapper mapper;

    public TurmaController(
        TurmaService servico,
        TurmaMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<TurmaDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC) // Mudar "nome" comforme a variavel em Turma
        }) 
        // @ParameterObject       Adicionar após adicionar springdoc nas dependencias 
        Pageable page) {
        Page<Turma> registros = servico.get(termoBusca, page);
        Page<TurmaDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> get(@PathVariable("id") Long id) {
        Turma registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TurmaDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<TurmaDto> insert(@RequestBody @Valid TurmaDto objeto) {
        Turma objetoConvertido = mapper.toEntity(objeto);
        Turma registro = servico.save(objetoConvertido);
        TurmaDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<TurmaDto> update(@RequestBody @Valid TurmaDto objeto) {
        Turma objetoConvertido = mapper.toEntity(objeto);
        Turma registro = servico.save(objetoConvertido);
        TurmaDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
