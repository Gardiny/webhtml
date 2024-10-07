package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record SkillDto(
    Long id,
    @NotBlank String nome,
    @NotBlank String descricao
) {

}
