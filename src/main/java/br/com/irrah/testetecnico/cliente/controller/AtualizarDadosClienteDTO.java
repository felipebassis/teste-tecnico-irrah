package br.com.irrah.testetecnico.cliente.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarDadosClienteDTO(
        @NotNull
        @NotBlank
        String nome,

        @NotNull
        @NotBlank
        String nomeResponsavel,

        @Email
        @NotNull
        @NotBlank
        String email,

        @NotBlank
        @NotNull
        String documentoResponsavel,

        @NotNull
        TelefoneDTO telefone
) {

}
