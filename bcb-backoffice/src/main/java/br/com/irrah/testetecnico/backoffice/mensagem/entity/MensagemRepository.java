package br.com.irrah.testetecnico.backoffice.mensagem.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {

    @Query("select count(m) from Mensagem m " +
            "where m.dataCriacao >= :inicio and " +
            "m.dataCriacao <= :fim and " +
            "m.id = :idCliente and " +
            "m.status in (" +
            "br.com.irrah.testetecnico.backoffice.mensagem.entity.StatusMensagem.A_ENVIAR, " +
            "br.com.irrah.testetecnico.backoffice.mensagem.entity.StatusMensagem.ENVIADA)")
    Long countByPeriodoAndCliente(
            LocalDateTime inicio,
            LocalDateTime fim,
            UUID idCliente
    );
}
