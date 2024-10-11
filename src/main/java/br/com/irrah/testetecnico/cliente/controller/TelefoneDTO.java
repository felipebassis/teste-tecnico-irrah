package br.com.irrah.testetecnico.cliente.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelefoneDTO(
        @NotNull
        @NotBlank
        String ddi,

        @NotNull
        @NotBlank
        String ddd,

        @NotNull
        @NotBlank
        String numero
) {
}
