package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.TurmaDto;
import com.softskills.softskills.model.Turma;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    @Mapping(target = "capacitacao_id", source = "capacitacao.id")
    @Mapping(target = "capacitacao_nome", source = "capacitacao.nome")
    TurmaDto toDto(Turma turma);

    @InheritInverseConfiguration
    Turma toEntity(TurmaDto dto);
}
