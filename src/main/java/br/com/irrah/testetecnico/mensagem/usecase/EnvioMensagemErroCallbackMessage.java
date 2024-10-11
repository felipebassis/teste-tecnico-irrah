package br.com.irrah.testetecnico.mensagem.usecase;

import java.util.UUID;

public record EnvioMensagemErroCallbackMessage(
        UUID idMensagem,
        String erro
) {
}
