package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorDto(
    Long id,
    @NotBlank String nome,
    @Email (
        message = "deve ser um domío de email valido",
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    String email,
    @NotBlank
    String telefone,
    Long usuario_id,
    String usuario_nome_completo,
    String usuario_nome_usuario
) {

}
