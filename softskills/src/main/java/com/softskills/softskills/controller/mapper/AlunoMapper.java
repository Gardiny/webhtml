package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.AlunoDto;
import com.softskills.softskills.model.Aluno;

@Mapper(componentModel = "spring")
public interface AlunoMapper{

    @Mapping(target = "usuario_id", source = "usuario.id")
    @Mapping(target = "usuario_nome_completo", source = "usuario.nome_completo")
    @Mapping(target = "usuario_nome_usuario", source = "usuario.nome_usuario")
    AlunoDto toDto(Aluno aluno);

    @InheritInverseConfiguration
    Aluno toEntity(AlunoDto dto);
}