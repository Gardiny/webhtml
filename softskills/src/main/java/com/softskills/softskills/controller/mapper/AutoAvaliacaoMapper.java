package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.AutoAvaliacaoDto;
import com.softskills.softskills.model.AutoAvaliacao;

@Mapper (componentModel = "spring")
public interface AutoAvaliacaoMapper {
    @Mapping(target = "aluno_id", source = "aluno.id")
    @Mapping(target = "aluno_nome", source = "aluno.nome")
    @Mapping(target = "skill_id", source = "skill.id")
    @Mapping(target = "skill_nome", source = "skill.nome")
    @Mapping(target = "disciplina_id", source = "disciplina.id")
    @Mapping(target = "disciplina_nome", source = "disciplina.nome")
    AutoAvaliacaoDto tDto(AutoAvaliacao autoavaliacao);

    @InheritInverseConfiguration
    AutoAvaliacao toEntity(AutoAvaliacaoDto dto);
}
