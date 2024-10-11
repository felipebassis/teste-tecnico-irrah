package br.com.irrah.testetecnico.cliente.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {


    @Modifying
    @Query("update Cliente c set c.plano.credito = c.plano.credito - (:valor * :quantidade) where c.id = :id")
    void deduzirSaldo(@Param("valor") BigDecimal valorPorMensagem,
                      @Param("quantidade") Long quantidadeMensagens,
                      @Param("id") UUID id);

    @Modifying
    @Query("update Cliente c set c.plano.credito = c.plano.credito + :custoPorMensagem where c.id = :id")
    void reembolsarSaldo(BigDecimal custoPorMensagem, UUID id);
}
