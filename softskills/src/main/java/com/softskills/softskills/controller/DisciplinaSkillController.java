package com.softskills.softskills.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softskills.softskills.controller.dto.DisciplinaSkillDto;
import com.softskills.softskills.controller.mapper.DisciplinaSkillMapper;
import com.softskills.softskills.model.DisciplinaSkill;
import com.softskills.softskills.model.DisciplinaSkillId;
import com.softskills.softskills.service.DisciplinaSkillService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/disciplina-skill", produces = MediaType.APPLICATION_JSON_VALUE)
public class DisciplinaSkillController {

    private final DisciplinaSkillService servico;
    private final DisciplinaSkillMapper mapper;

    public DisciplinaSkillController(
        DisciplinaSkillService servico,
        DisciplinaSkillMapper mapper){
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public ResponseEntity<Page<DisciplinaSkillDto>> get(
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        Pageable page) {
        Page<DisciplinaSkill> registros = servico.get(page);
        Page<DisciplinaSkillDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{disciplinaId}/{skillId}")
    public ResponseEntity<DisciplinaSkillDto> get(@PathVariable Long disciplinaId, @PathVariable Long skillId){
        DisciplinaSkillId id = new DisciplinaSkillId(disciplinaId,skillId);
        DisciplinaSkill registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DisciplinaSkillDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public ResponseEntity<DisciplinaSkillDto> insert(@RequestBody @Valid DisciplinaSkillDto objeto) {
        DisciplinaSkill registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(objeto);
    }

    // esse Put não pode editar as chaves compostas, vai ter que deletar e criar novamente.
    // deixar qui caso essa tabela tenha alguma coluna não chave.
    @PutMapping("/{disciplinaid}/{skillid}")
    public ResponseEntity<DisciplinaSkillDto> update(@PathVariable Long disciplinaid, @PathVariable Long skillid,
                                                @RequestBody @Valid DisciplinaSkillDto objeto) {
        DisciplinaSkillId id = new DisciplinaSkillId(disciplinaid,skillid);                                            
        DisciplinaSkill existe = servico.get(id);
        if (existe == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DisciplinaSkill objConvertido = mapper.toEntity(objeto);
        DisciplinaSkill registro = servico.save(objeto);
        DisciplinaSkillDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{disciplinaid}/{skillid}")
    public ResponseEntity<Void> delete(@PathVariable Long disciplinaid, @PathVariable Long skillid) {
        DisciplinaSkillId id = new DisciplinaSkillId(disciplinaid, skillid);
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

