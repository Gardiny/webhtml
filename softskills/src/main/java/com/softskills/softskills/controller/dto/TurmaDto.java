package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TurmaDto(
    Long id,
    @NotBlank String nome,
    @NotNull Integer quant_vagas,
    Long capacitacao_id,
    String capacitacao_nome
) {

}
