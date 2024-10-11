package br.com.irrah.testetecnico.mensagem;

import java.util.UUID;

public record EnvioMensagemErroCallbackMessage(
        UUID idMensagem,
        String erro
) {
}
