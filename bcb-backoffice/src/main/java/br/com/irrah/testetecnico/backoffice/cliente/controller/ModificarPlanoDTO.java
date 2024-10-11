package br.com.irrah.testetecnico.backoffice.cliente.controller;

import br.com.irrah.testetecnico.backoffice.cliente.entity.TipoPlano;

import java.math.BigDecimal;

public record ModificarPlanoDTO(
        TipoPlano tipoPlano,
        BigDecimal novoSaldo,
        BigDecimal novoLimite
) {
}
