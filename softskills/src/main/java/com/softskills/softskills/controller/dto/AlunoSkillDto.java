package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.NotNull;

public record AlunoSkillDto(
    @NotNull Long aluno_id,
    String aluno_nome,
    @NotNull Long skill_id,
    String skill_nome,
    String skill_tipo,
    String nota_final
) {

}
