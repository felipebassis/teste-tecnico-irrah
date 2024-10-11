package br.com.irrah.testetecnico.backoffice.cliente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Embeddable
public class InformacoesPlano {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano")
    private TipoPlano tipoPlano;

    @Column(name = "credito")
    private BigDecimal credito;

    @Column(name = "limite_consumo")
    private BigDecimal limiteConsumo;
}
