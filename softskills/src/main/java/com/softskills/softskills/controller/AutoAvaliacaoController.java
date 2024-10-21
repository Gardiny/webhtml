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

import com.softskills.softskills.controller.dto.AutoAvaliacaoDto;
import com.softskills.softskills.controller.mapper.AutoAvaliacaoMapper;
import com.softskills.softskills.model.AutoAvaliacao;
import com.softskills.softskills.service.AutoAvaliacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/autoavaliacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutoAvaliacaoController implements IController<AutoAvaliacaoDto>{
    
    private final AutoAvaliacaoService servico;
    private final AutoAvaliacaoMapper mapper;

    public AutoAvaliacaoController(
        AutoAvaliacaoService servico,
        AutoAvaliacaoMapper mapper
        ){
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<AutoAvaliacaoDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false,defaultValue = "false") boolean unpaged,
        Pageable page) {
        Page<AutoAvaliacao> registros = servico.get(termoBusca, page);
        Page<AutoAvaliacaoDto> dtos = registros.map(mapper::tDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AutoAvaliacaoDto> get(@PathVariable("id") Long id) {
        AutoAvaliacao registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        AutoAvaliacaoDto dto = mapper.tDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<AutoAvaliacaoDto> insert(@RequestBody @Valid AutoAvaliacaoDto objeto) {
        AutoAvaliacao objconvertido = mapper.toEntity(objeto);
        AutoAvaliacao registro = servico.save(objconvertido);
        AutoAvaliacaoDto dto = mapper.tDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<AutoAvaliacaoDto> update(@RequestBody @Valid AutoAvaliacaoDto objeto) {
        AutoAvaliacao objconvertido = mapper.toEntity(objeto);
        AutoAvaliacao registro = servico.save(objconvertido);
        AutoAvaliacaoDto dto = mapper.tDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<AutoAvaliacao> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
