package br.com.irrah.testetecnico.cliente.controller;

import br.com.irrah.testetecnico.cliente.entity.TipoPlano;

import java.math.BigDecimal;
import java.util.UUID;

public record DadosClienteDTO(
        UUID id,
        String email,
        String nome,
        String documento,
        String documentoResponsavel,
        TelefoneDTO telefone,
        TipoPlano tipoPlano,
        BigDecimal credito,
        BigDecimal limiteConsumo
) {
}
