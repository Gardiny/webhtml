package com.softskills.softskills.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.softskills.softskills.controller.dto.TurmaAlunoDto;
import com.softskills.softskills.controller.dto.TurmaDisciplinaDto;
import com.softskills.softskills.controller.mapper.TurmaDisciplinaMapper;
import com.softskills.softskills.model.TurmaAluno;
import com.softskills.softskills.model.TurmaDisciplina;
import com.softskills.softskills.model.TurmaDisciplinaId;
import com.softskills.softskills.service.TurmaDisciplinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/turma-disciplina", produces = MediaType.APPLICATION_JSON_VALUE)
public class TurmaDisciplinaController {
    private final TurmaDisciplinaService servico;
    private final TurmaDisciplinaMapper mapper;

    public TurmaDisciplinaController(
        TurmaDisciplinaService servico,
        TurmaDisciplinaMapper mapper){
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public ResponseEntity<Page<TurmaDisciplinaDto>> get(
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        Pageable page) {
        Page<TurmaDisciplina> registros = servico.get(page);
        Page<TurmaDisciplinaDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/turmaId/{turmaId}")
    public ResponseEntity<List<TurmaDisciplinaDto>> getByTurma(@PathVariable Long turmaId
        ) {
        List<TurmaDisciplina> registros = servico.getByTurma(turmaId);
        List<TurmaDisciplinaDto> dtos = registros.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/disciplinaId/{disciplinaId}")
    public ResponseEntity<List<TurmaDisciplinaDto>> getByDisciplina(@PathVariable Long disciplinaId
        ) {
        List<TurmaDisciplina> registros = servico.getByDisciplina(disciplinaId);
        List<TurmaDisciplinaDto> dtos = registros.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/professorId/{professorId}")
    public ResponseEntity<List<TurmaDisciplinaDto>> getByProfessor(@PathVariable Long professorId
        ) {
        List<TurmaDisciplina> registros = servico.getByProfessor(professorId);
        List<TurmaDisciplinaDto> dtos = registros.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{turmaid}/{disciplinaid}/{professorid}")
    public ResponseEntity<TurmaDisciplinaDto> get(@PathVariable Long turmaid, @PathVariable Long disciplinaid, @PathVariable Long professorid){
        TurmaDisciplinaId id = new TurmaDisciplinaId(turmaid,disciplinaid,professorid);
        TurmaDisciplina registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TurmaDisciplinaDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public ResponseEntity<TurmaDisciplina> insert(@RequestBody @Valid TurmaDisciplinaDto objeto) {
        TurmaDisciplina registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    // esse Put não pode editar as chaves compostas, vai ter que deletar e criar novamente.
    // deixar qui caso essa tabela tenha alguma coluna não chave.
    @PutMapping("/{turmaid}/{Disciplinaid}")
    public ResponseEntity<TurmaDisciplinaDto> update(@PathVariable Long turmaid, @PathVariable Long disciplinaid, @PathVariable Long professorid,
                                                @RequestBody @Valid TurmaDisciplinaDto objeto) {
        TurmaDisciplinaId id = new TurmaDisciplinaId(turmaid,disciplinaid,professorid);                                            
        TurmaDisciplina existe = servico.get(id);
        if (existe == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        mapper.toEntity(objeto);
        TurmaDisciplina registro = servico.save(objeto);
        TurmaDisciplinaDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{turmaid}/{disciplinaid}/{professorid}")
    public ResponseEntity<Void> delete(@PathVariable Long turmaid, @PathVariable Long disciplinaid, @PathVariable Long professorid) {
        TurmaDisciplinaId id = new TurmaDisciplinaId(turmaid, disciplinaid, professorid);
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
