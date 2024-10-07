package com.softskills.softskills.controller;

import java.util.List;
import java.util.stream.Collector;
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
import com.softskills.softskills.controller.mapper.AlunoSkillMapper;
import com.softskills.softskills.model.AlunoSkill;
import com.softskills.softskills.model.AlunoSkillId;
import com.softskills.softskills.service.AlunoSkillService;

import jakarta.validation.Valid;



@RestController
@RequestMapping(value = "/aluno-skill", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlunoSkillController {

    private final AlunoSkillService servico;
    private final AlunoSkillMapper mapper;

    public AlunoSkillController(
        AlunoSkillService servico,
        AlunoSkillMapper mapper){
        this.servico = servico;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public ResponseEntity<Page<AlunoSkillDto>> get(
        @RequestParam(required = false, defaultValue = "false") boolean unpaged,
        Pageable page) {
        Page<AlunoSkill> registros = servico.get(page);
        Page<AlunoSkillDto> dtos = registros.map(mapper::toDto);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{alunoId}")
    public ResponseEntity<List<AlunoSkillDto>> get(@PathVariable Long alunoId
        ) {
        List<AlunoSkill> registros = servico.get(alunoId);
        List<AlunoSkillDto> dtos = registros.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    
    @GetMapping("/{alunoId}/{skillId}")
    public ResponseEntity<AlunoSkillDto> get(@PathVariable Long alunoId, @PathVariable Long skillId){
        AlunoSkillId id = new AlunoSkillId(alunoId,skillId);
        AlunoSkill registro = servico.get(id);
        if (registro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        AlunoSkillDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/")
    public ResponseEntity<AlunoSkillDto> insert(@RequestBody @Valid AlunoSkillDto objeto) {
        AlunoSkill registro = servico.save(objeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(objeto);
    }

    @PutMapping("/{alunois}/{skillid}")
    public ResponseEntity<AlunoSkillDto> update(@PathVariable Long alunois, @PathVariable Long skillid,
                                                @RequestBody @Valid AlunoSkillDto objeto) {
        AlunoSkillId id = new AlunoSkillId(alunois,skillid);                                            
        AlunoSkill existe = servico.get(id);
        if (existe == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        AlunoSkill objConvertido = mapper.toEntity(objeto);
        AlunoSkill registro = servico.save(objeto);
        AlunoSkillDto dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

        @DeleteMapping("/{alunoid}/{skillid}")
    public ResponseEntity<Void> delete(@PathVariable Long alunoid, @PathVariable Long skillid) {
        AlunoSkillId id = new AlunoSkillId(alunoid, skillid);
        servico.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
