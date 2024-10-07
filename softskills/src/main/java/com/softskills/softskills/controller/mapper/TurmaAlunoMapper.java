package com.softskills.softskills.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softskills.softskills.controller.dto.TurmaAlunoDto;
import com.softskills.softskills.model.TurmaAluno;
import com.softskills.softskills.model.TurmaAlunoId;

@Mapper(componentModel = "spring")
public interface TurmaAlunoMapper {

    @Mapping(target = "turma_id", source = "id.turma")
    @Mapping(target = "turma_nome", source = "turma.nome")
    @Mapping(target = "aluno_id", source = "id.aluno")
    @Mapping(target = "aluno_nome", source = "aluno.nome")
    TurmaAlunoDto toDto(TurmaAluno turmaAluno);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    TurmaAluno toEntity(TurmaAlunoDto dto);

    default TurmaAluno fromDto(TurmaAlunoDto dto){
        TurmaAlunoId id = new TurmaAlunoId(dto.turma_id(),dto.aluno_id());
        TurmaAluno entity = toEntity(dto);
        entity.setId(id);
        return entity;
    }
}
