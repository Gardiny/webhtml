package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
    Long id,
    @NotBlank String nome_completo,
    @NotBlank String nome_usuario,
    @Email (
        message = "deve ser um domío de email valido",
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    String email,
    @NotBlank String papel,
    @NotBlank String senha,
    boolean ativo
) {

}
