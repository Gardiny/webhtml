// package com.softskills.softskills.controller.mapper;

// import org.mapstruct.InheritInverseConfiguration;
// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

// import com.softskills.softskills.controller.dto.DisciplinaDto;
// import com.softskills.softskills.model.Disciplina;

// @Mapper(componentModel = "spring")
// public interface DisciplinaMapper {

//     @Mapping(target = "turma_id", source = "turma.id")
//     @Mapping(target = "turma_nome", source = "turma.nome")
//     @Mapping(target = "professor_id", source = "professor.id")
//     @Mapping(target = "professor_nome", source = "professor.nome")
//     DisciplinaDto toDto(Disciplina diciplina);

//     @InheritInverseConfiguration
//     Disciplina toEntity(DisciplinaDto dto);
// }
