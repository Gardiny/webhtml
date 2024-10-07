package com.softskills.softskills.controller.dto;


import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CapacitacaoDto(
    Long id,
    @NotBlank String nome,
    @NotBlank String carga_horaria,
    @NotNull LocalDate data_inicio,
    @NotNull LocalDate data_fim,
    Long coordenador_id,
    String coordenador_nome
) {

}
