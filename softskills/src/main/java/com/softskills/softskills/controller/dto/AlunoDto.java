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
        regexp = "^(.+)@^(+)")
    String email,
    @NotBlank
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$" )
    String telefone,
    String sexo,
    String status
) {
}
