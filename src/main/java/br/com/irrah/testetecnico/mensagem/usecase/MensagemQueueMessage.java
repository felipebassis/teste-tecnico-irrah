package br.com.irrah.testetecnico.mensagem.usecase;

import java.util.UUID;

public record MensagemQueueMessage(
        UUID id,
        UUID idCliente,
        String telefone,
        String plataformaEnvio,
        String texto
) {
}
