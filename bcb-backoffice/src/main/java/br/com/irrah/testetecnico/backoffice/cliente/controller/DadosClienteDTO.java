package br.com.irrah.testetecnico.backoffice.cliente.controller;

import java.util.UUID;

public record DadosClienteDTO(
        UUID id,
        String email,
        String nome,
        String documento,
        String documentoResponsavel,
        TelefoneDTO telefone
) {
}
