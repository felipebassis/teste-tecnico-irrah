package br.com.irrah.testetecnico.backoffice.cliente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "documento")
    private String documento;

    @Column(name = "documento_responsavel")
    private String documentoResponsavel;

    @Column(name = "telefone")
    @Convert(converter = Telefone.TelefoneConverter.class)
    private Telefone telefone;

    @Embedded
    private InformacoesPlano plano;

}
