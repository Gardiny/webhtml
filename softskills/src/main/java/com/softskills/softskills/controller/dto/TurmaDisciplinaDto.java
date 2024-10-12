package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.NotNull;

public record TurmaDisciplinaDto(
    @NotNull Long turma_id,
    String turma_nome,
    @NotNull Long disciplina_id,
    String disciplina_nome,
    @NotNull Long professor_id,
    String professor_nome
) {

}
