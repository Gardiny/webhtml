package com.softskills.softskills.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softskills.softskills.controller.dto.AvaliacaoDto;
import com.softskills.softskills.controller.mapper.AvaliacaoMapper;
import com.softskills.softskills.model.Avaliacao;
import com.softskills.softskills.service.AvaliacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/avaliacao",produces = MediaType.APPLICATION_JSON_VALUE)
public class AvaliacaoController implements IController<AvaliacaoDto>{

    private final AvaliacaoService servico;
    private final AvaliacaoMapper mapper;

    public AvaliacaoController(
        AvaliacaoService servico,
        AvaliacaoMapper mapper
        ){
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<AvaliacaoDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false,defaultValue = "false") boolean unpaged,
        Pageable page) {
        Page<Avaliacao> registros = servico.get(termoBusca, page);
        Page<AvaliacaoDto> dtos = registros.map(mapper::tDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDto> get(@PathVariable("id") Long id) {
        Avaliacao registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        AvaliacaoDto dto = mapper.tDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<AvaliacaoDto> insert(@RequestBody @Valid AvaliacaoDto objeto) {
        Avaliacao objconvertido = mapper.toEntity(objeto);
        Avaliacao registro = servico.save(objconvertido);
        AvaliacaoDto dto = mapper.tDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<AvaliacaoDto> update(@RequestBody @Valid AvaliacaoDto objeto) {
        Avaliacao objconvertido = mapper.toEntity(objeto);
        Avaliacao registro = servico.save(objconvertido);
        AvaliacaoDto dto = mapper.tDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Avaliacao> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
