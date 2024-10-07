package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.NotNull;

public record DisciplinaSkillDto(
    @NotNull Long disciplina_id,
    String disciplina_nome,
    @NotNull Long skill_id,
    String skill_nome
) {


}
