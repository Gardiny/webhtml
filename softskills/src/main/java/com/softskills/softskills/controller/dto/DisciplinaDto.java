package com.softskills.softskills.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplinaDto(
    Long id,
    @NotBlank String codigo,
    @NotBlank String nome,
    @NotBlank String carga_horaria,
    @NotNull LocalDate data_inicio,
    @NotNull LocalDate data_fim
) {

}
