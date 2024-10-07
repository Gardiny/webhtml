package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record DisciplinaDto(
    Long id,
    @NotBlank String codigo,
    @NotBlank String nome,
    @NotBlank String carga_horaria,
    Long turma_id,
    String turma_nome,
    Long professor_id,
    String professor_nome
) {

}
