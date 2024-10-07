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

import com.softskills.softskills.controller.dto.CapacitacaoDto;
import com.softskills.softskills.controller.mapper.CapacitacaoMapper;
import com.softskills.softskills.model.Capacitacao;
import com.softskills.softskills.service.CapacitacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/capacitacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class CapacitacaoController implements IController<CapacitacaoDto>{
    
    private final CapacitacaoService servico;
    private final CapacitacaoMapper mapper;

    public CapacitacaoController(
        CapacitacaoService servico,
        CapacitacaoMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Page<CapacitacaoDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC) // Mudar "nome" comforme a variavel em Capacitacao
        }) 
        // @ParameterObject       Adicionar após adicionar springdoc nas dependencias 
        Pageable page) {
        Page<Capacitacao> registros = servico.get(termoBusca, page);
        Page<CapacitacaoDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CapacitacaoDto> get(@PathVariable("id") Long id) {
        Capacitacao registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        CapacitacaoDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<CapacitacaoDto> insert(@RequestBody @Valid CapacitacaoDto objeto) {
        Capacitacao objetoConvertido = mapper.toEntity(objeto);
        Capacitacao registro = servico.save(objetoConvertido);
        CapacitacaoDto dto = mapper.toDto(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<CapacitacaoDto> update(@RequestBody @Valid CapacitacaoDto objeto) {
        Capacitacao objetoConvertido = mapper.toEntity(objeto);
        Capacitacao registro = servico.save(objetoConvertido);
        CapacitacaoDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);


}
}
