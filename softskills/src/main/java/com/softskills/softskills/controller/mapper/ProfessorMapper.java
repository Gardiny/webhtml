package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.ProfessorDto;
import com.softskills.softskills.model.Professor;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    @Mapping(target = "usuario_id", source = "usuario.id")
    @Mapping(target = "usuario_nome", source = "usuario.nome_completo")
    ProfessorDto toDto(Professor professor);

    @InheritInverseConfiguration
    Professor toEntity(ProfessorDto dto);
}
