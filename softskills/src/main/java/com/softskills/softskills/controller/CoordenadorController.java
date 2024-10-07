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

import com.softskills.softskills.controller.dto.CoordenadorDto;
import com.softskills.softskills.controller.mapper.CoordenadorMapper;
import com.softskills.softskills.model.Coordenador;
import com.softskills.softskills.service.CoordenadorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/coordenador",produces = MediaType.APPLICATION_JSON_VALUE)
public class CoordenadorController implements IController<CoordenadorDto>{

    
    private final CoordenadorService servico;
    private final CoordenadorMapper mapper;

    public CoordenadorController(
        CoordenadorService servico,
        CoordenadorMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<CoordenadorDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC) // Mudar "nome" comforme a variavel em Coordenador
        }) 
        // @ParameterObject       Adicionar após adicionar springdoc nas dependencias 
        Pageable page) {
        Page<Coordenador> registros = servico.get(termoBusca, page);
        Page<CoordenadorDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorDto> get(@PathVariable("id") Long id) {
        Coordenador registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        CoordenadorDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<CoordenadorDto> insert(@RequestBody @Valid CoordenadorDto objeto) {
        Coordenador objetoConvertido = mapper.toEntity(objeto);
        Coordenador registro = servico.save(objetoConvertido);
        CoordenadorDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<CoordenadorDto> update(@RequestBody @Valid CoordenadorDto objeto) {
        Coordenador objetoConvertido = mapper.toEntity(objeto);
        Coordenador registro = servico.save(objetoConvertido);
        CoordenadorDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
