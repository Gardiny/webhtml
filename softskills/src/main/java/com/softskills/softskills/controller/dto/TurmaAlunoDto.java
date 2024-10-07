package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.NotNull;

public record TurmaAlunoDto(
    @NotNull Long turma_id,
    String turma_nome,
    @NotNull Long aluno_id,
    String aluno_nome
) {

}
