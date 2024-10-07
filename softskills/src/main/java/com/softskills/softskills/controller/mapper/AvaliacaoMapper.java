package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.AvaliacaoDto;
import com.softskills.softskills.model.Avaliacao;

@Mapper (componentModel = "spring")
public interface AvaliacaoMapper {
    @Mapping(target = "professor_id", source = "professor.id")
    @Mapping(target = "professor_nome", source = "professor.nome")
    @Mapping(target = "aluno_id", source = "aluno.id")
    @Mapping(target = "aluno_nome", source = "aluno.nome")
    @Mapping(target = "skill_id", source = "skill.id")
    @Mapping(target = "skill_nome", source = "skill.nome")
    @Mapping(target = "disciplina_id", source = "disciplina.id")
    @Mapping(target = "disciplina_nome", source = "disciplina.nome")
    AvaliacaoDto tDto(Avaliacao avaliacao);

    @InheritInverseConfiguration
    Avaliacao toEntity(AvaliacaoDto dto);

}
