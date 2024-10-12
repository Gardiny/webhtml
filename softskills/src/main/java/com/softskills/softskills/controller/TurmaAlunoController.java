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

import com.softskills.softskills.controller.dto.AlunoSkillDto;
import com.softskills.softskills.controller.dto.TurmaAlunoDto;
import com.softskills.softskills.controller.mapper.TurmaAlunoMapper;
import com.softskills.softskills.model.AlunoSkill;
import com.softskills.softskills.model.TurmaAluno;
import com.softskills.softskills.model.TurmaAlunoId;
import com.softskills.softskills.service.TurmaAlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/turma-aluno", produces = MediaType.APPLICATION_JSON_VALUE)
public class TurmaAlunoController {
    private final TurmaAlunoService servico;
    private final TurmaAlunoMapper mapper;

    public TurmaAlunoController(
        TurmaAlunoService servico,
        TurmaAlunoMapper mapper){
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public ResponseEntity<Page<TurmaAlunoDto>> get(
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        Pageable page) {
        Page<TurmaAluno> registros = servico.get(page);
        Page<TurmaAlunoDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/turmaId/{turmaId}")
    public ResponseEntity<List<TurmaAlunoDto>> getByTurma(@PathVariable Long turmaId
        ) {
        List<TurmaAluno> registros = servico.getByTurma(turmaId);
        List<TurmaAlunoDto> dtos = registros.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/alunoId/{alunoId}")
    public ResponseEntity<List<TurmaAlunoDto>> getByAluno(@PathVariable Long alunoId
        ) {
        List<TurmaAluno> registros = servico.getbyAluno(alunoId);
        List<TurmaAlunoDto> dtos = registros.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{turmaid}/{alunoid}")
    public ResponseEntity<TurmaAlunoDto> get(@PathVariable Long turmaid, @PathVariable Long alunoid){
        TurmaAlunoId id = new TurmaAlunoId(turmaid,alunoid);
        TurmaAluno registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TurmaAlunoDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public ResponseEntity<TurmaAluno> insert(@RequestBody @Valid TurmaAlunoDto objeto) {
        TurmaAluno registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registro);
    }

    // esse Put não pode editar as chaves compostas, vai ter que deletar e criar novamente.
    // deixar qui caso essa tabela tenha alguma coluna não chave.
    @PutMapping("/{turmaid}/{alunoid}")
    public ResponseEntity<TurmaAlunoDto> update(@PathVariable Long turmaid, @PathVariable Long alunoid,
                                                @RequestBody @Valid TurmaAlunoDto objeto) {
        TurmaAlunoId id = new TurmaAlunoId(turmaid,alunoid);                                            
        TurmaAluno existe = servico.get(id);
        if (existe == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        mapper.toEntity(objeto);
        TurmaAluno registro = servico.save(objeto);
        TurmaAlunoDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{turmaid}/{alunoid}")
    public ResponseEntity<Void> delete(@PathVariable Long turmaid, @PathVariable Long alunoid) {
        TurmaAlunoId id = new TurmaAlunoId(turmaid, alunoid);
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
