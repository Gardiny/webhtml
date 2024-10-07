package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.AlunoSkillDto;
import com.softskills.softskills.model.AlunoSkill;
import com.softskills.softskills.model.AlunoSkillId;

@Mapper (componentModel = "spring")
public interface AlunoSkillMapper {
    @Mapping(target = "aluno_id", source = "id.aluno")
    @Mapping(target = "aluno_nome", source = "aluno.nome")
    @Mapping(target = "skill_id", source = "id.skill")
    @Mapping(target = "skill_nome", source = "skill.nome")
    @Mapping(target = "skill_tipo", source = "skill.tipo")
    AlunoSkillDto toDto(AlunoSkill alunoSkill);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    AlunoSkill toEntity(AlunoSkillDto dto);

    default AlunoSkill fromDto(AlunoSkillDto dto) {
        AlunoSkillId id = new AlunoSkillId(dto.aluno_id(),dto.skill_id());
        AlunoSkill entity = toEntity(dto);
        entity.setId(id);
        return entity;
    }
}
