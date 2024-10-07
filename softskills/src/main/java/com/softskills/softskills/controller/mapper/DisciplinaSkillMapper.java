package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.DisciplinaSkillDto;
import com.softskills.softskills.model.DisciplinaSkill;
import com.softskills.softskills.model.DisciplinaSkillId;

@Mapper (componentModel = "spring")
public interface DisciplinaSkillMapper {
    @Mapping(target = "disciplina_id", source = "id.disciplinaId")
    @Mapping(target = "disciplina_nome",source = "disciplina.nome")
    @Mapping(target = "skill_id", source = "id.skillId")
    @Mapping(target = "skill_nome", source = "skill.nome")
    DisciplinaSkillDto toDto(DisciplinaSkill DisciplinaSkill);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    DisciplinaSkill toEntity(DisciplinaSkillDto dto);

    default DisciplinaSkill fromDto(DisciplinaSkillDto dto) {
        DisciplinaSkillId id = new DisciplinaSkillId(dto.disciplina_id(), dto.skill_id());
        DisciplinaSkill entity = toEntity(dto);
        entity.setId(id);
        return entity;
    }
}
