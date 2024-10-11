package br.com.irrah.testetecnico.backoffice.cliente.controller;

import br.com.irrah.testetecnico.backoffice.cliente.entity.TipoPlano;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

public record InformacaoPlanoDTO(
        UUID idCliente,
        TipoPlano tipoPlano,
        BigDecimal creditos,
        @Nullable
        BigDecimal limite,
        @Nullable
        BigDecimal limiteGasto
) {
}
