package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// mudar conforme os atributos dos models

public record AlunoDto(

    Long id,
    @NotBlank String nome,
    @NotBlank String cpf,
    @Email (
        message = "deve ser um domío de email valido",
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    String email,
    @NotBlank
    String telefone,
    String sexo,
    String status,
    Long usuario_id,
    String usuario_nome_completo,
    String usuario_nome_usuario
) {
}
