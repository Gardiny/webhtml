package com.softskills.softskills.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
    Long id,
    @NotBlank String nome_completo,
    @NotBlank String nome_usuario,
    @NotBlank String papel,
    @NotBlank String senha,
    boolean ativo
) {

}
