package br.com.irrah.testetecnico.backoffice.mensagem.entity;

import br.com.irrah.testetecnico.backoffice.cliente.entity.Cliente;
import br.com.irrah.testetecnico.backoffice.cliente.entity.Telefone;
import br.com.irrah.testetecnico.backoffice.cliente.entity.Telefone.TelefoneConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "numero_telefone")
    @Convert(converter = TelefoneConverter.class)
    private Telefone numeroTelefone;

    @Column(name = "texto")
    private String texto;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma")
    private PlataformaEnvio plataforma;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusMensagem status = StatusMensagem.A_ENVIAR;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
}
