package com.softskills.softskills.controller.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutoAvaliacaoDto(
    Long id,
    @NotBlank String nota,
    @NotNull LocalDateTime data,
    @NotNull Long aluno_id,
    String aluno_nome,
    @NotNull Long skill_id,
    String skill_nome,
    @NotNull Long disciplina_id,
    String disciplina_nome
) {

}
