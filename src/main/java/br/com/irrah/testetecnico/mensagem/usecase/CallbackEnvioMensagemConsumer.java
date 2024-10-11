package br.com.irrah.testetecnico.mensagem.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CallbackEnvioMensagemConsumer {

    private static final String QUEUE = "enviar_mensagem_callback_queue";

    private final ObjectMapper objectMapper;

    private final EnviarMensagemUseCase enviarMensagemUseCase;

    @JmsListener(destination = QUEUE)
    void processarCallback(String mensagemString) throws JsonProcessingException {
        var mensagem = objectMapper.readValue(mensagemString, EnvioMensagemCallbackMessage.class);
        enviarMensagemUseCase.atualizarStatusParaEnviado(mensagem.idMensagem());
    }
}
