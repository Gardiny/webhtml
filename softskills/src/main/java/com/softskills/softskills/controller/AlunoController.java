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

import com.softskills.softskills.controller.dto.AlunoDto;
import com.softskills.softskills.controller.mapper.AlunoMapper;
import com.softskills.softskills.model.Aluno;
import com.softskills.softskills.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/aluno",produces = MediaType.APPLICATION_JSON_VALUE)
public class AlunoController implements IController<AlunoDto>{

    private final AlunoService servico;
    private final AlunoMapper mapper;

    public AlunoController(
        AlunoService servico,
        AlunoMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public ResponseEntity<Page<AlunoDto>> get(
        @RequestParam(required = false) String termoBusca,
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        @SortDefault.SortDefaults({
            @SortDefault(sort = "nome", direction = Sort.Direction.ASC) // Mudar "nome" comforme a variavel em aluno
        }) 
        // @ParameterObject       Adicionar após adicionar springdoc nas dependencias 
        Pageable page) {
        Page<Aluno> registros = servico.get(termoBusca, page);
        Page<AlunoDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> get(@PathVariable("id") Long id) {
        Aluno registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        AlunoDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public ResponseEntity<AlunoDto> insert(@RequestBody @Valid AlunoDto objeto) {
        Aluno objetoConvertido = mapper.toEntity(objeto);
        Aluno registro = servico.save(objetoConvertido);
        AlunoDto dto = mapper.toDto(registro); 
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


    @PutMapping("/")
    public ResponseEntity<AlunoDto> update(@RequestBody @Valid AlunoDto objeto){
        Aluno objetoConvertido = mapper.toEntity(objeto);
        Aluno registro = servico.save(objetoConvertido);
        AlunoDto dto = mapper.toDto(registro); 
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
