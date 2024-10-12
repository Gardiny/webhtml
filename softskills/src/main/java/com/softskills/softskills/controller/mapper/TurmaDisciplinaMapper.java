package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.TurmaDisciplinaDto;
import com.softskills.softskills.model.TurmaDisciplina;
import com.softskills.softskills.model.TurmaDisciplinaId;

@Mapper(componentModel = "spring")
public interface TurmaDisciplinaMapper {

    @Mapping(target = "turma_id", source = "id.turma")
    @Mapping(target = "turma_nome", source = "turma.nome")
    @Mapping(target = "disciplina_id", source = "id.disciplina")
    @Mapping(target = "disciplina_nome", source = "disciplina.nome")
    @Mapping(target = "professor_id", source = "id.professor")
    @Mapping(target = "professor_nome", source = "professor.nome")
    TurmaDisciplinaDto toDto(TurmaDisciplina turmaDisciplina);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    TurmaDisciplina toEntity(TurmaDisciplinaDto dto);

    default TurmaDisciplina fromDto(TurmaDisciplinaDto dto){
        TurmaDisciplinaId id = new TurmaDisciplinaId(dto.turma_id(),dto.disciplina_id(),dto.professor_id());
        TurmaDisciplina entity = toEntity(dto);
        entity.setId(id);
        return entity;
    }
}
