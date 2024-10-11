package br.com.irrah.testetecnico.mensagem.usecase;

import java.util.UUID;

public record EnvioMensagemCallbackMessage(
        UUID idMensagem
) {
}
