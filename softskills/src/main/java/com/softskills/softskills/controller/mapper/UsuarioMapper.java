package com.softskills.softskills.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.UsuarioDto;
import com.softskills.softskills.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto(Usuario usuario);

    @Mapping(target = "senha", ignore = true)
    Usuario toEntity(UsuarioDto dto);
}