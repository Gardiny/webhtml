package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.CapacitacaoDto;
import com.softskills.softskills.model.Capacitacao;

@Mapper (componentModel = "spring")
public interface CapacitacaoMapper {
    @Mapping(target = "coordenador_id", source = "coordenador.id")
    @Mapping(target = "coordenador_nome", source = "coordenador.nome")
    CapacitacaoDto toDto(Capacitacao capacitacao);

    @InheritInverseConfiguration
    Capacitacao toEntity(CapacitacaoDto dto);

}
