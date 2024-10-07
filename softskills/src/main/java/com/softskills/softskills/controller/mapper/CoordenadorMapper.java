package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.CoordenadorDto;
import com.softskills.softskills.model.Coordenador;

@Mapper(componentModel = "spring")
public interface CoordenadorMapper {

    @Mapping(target = "usuario_id", source = "usuario.id")
    @Mapping(target = "usuario_nome", source = "usuario.nome_completo")
    CoordenadorDto toDto(Coordenador coordenador);

    @InheritInverseConfiguration
    Coordenador toEntity(CoordenadorDto dto);
}
