package br.com.irrah.testetecnico.cliente.entity;

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
import lombok.Setter;
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

    @Setter
    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "nome")
    private String nome;

    @Setter
    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "documento")
    private String documento;

    @Setter
    @Column(name = "documento_responsavel")
    private String documentoResponsavel;

    @Setter
    @Column(name = "telefone")
    @Convert(converter = Telefone.TelefoneConverter.class)
    private Telefone telefone;

    @Embedded
    private InformacoesPlano plano;

}
