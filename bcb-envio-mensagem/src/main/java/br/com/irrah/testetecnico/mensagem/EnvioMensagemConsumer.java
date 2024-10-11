package br.com.irrah.testetecnico.mensagem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class EnvioMensagemConsumer {

    private static final String QUEUE = "enviar_mensagem_queue";

    private static final String CALLBACK_QUEUE = "enviar_mensagem_callback_queue";

    private static final String ERROR_CALLBACK_QUEUE = "enviar_mensagem_error_callback_queue";

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    private final List<EnviarMensagemUseCase> enviarMensagemUseCases;

    @JmsListener(destination = QUEUE)
    void enviarMensagem(String mensagemAsJson) throws JsonProcessingException {
        var mensagem = objectMapper.readValue(mensagemAsJson, MensagemQueueMessage.class);
        try {
            var useCaseEnvioPlataforma = enviarMensagemUseCases.stream()
                    .filter(useCase -> useCase.getPlataforma().equals(PlataformaEnvio.getPorDescricao(mensagem.plataformaEnvio())))
                    .findFirst();
            if (useCaseEnvioPlataforma.isEmpty()) {
                log.warn("Plataforma {} incompatível, ignorando mensagem...", mensagem.plataformaEnvio());
                return;
            }
            useCaseEnvioPlataforma.get().enviarMensagem(mensagem);
            jmsTemplate.convertAndSend(CALLBACK_QUEUE, objectMapper.writeValueAsString(new EnvioMensagemCallbackMessage(mensagem.id())));
        } catch (EnvioMensagemException e) {
            log.error("Erro ao enviar mensagem.", e);
            jmsTemplate.convertAndSend(ERROR_CALLBACK_QUEUE, objectMapper.writeValueAsString(new EnvioMensagemErroCallbackMessage(mensagem.id(), e.getMessage())));
        } catch (Exception e) {
            log.error("Erro inesperado ao enviar mensagem.", e);
            jmsTemplate.convertAndSend(ERROR_CALLBACK_QUEUE, objectMapper.writeValueAsString(new EnvioMensagemErroCallbackMessage(mensagem.id(), e.getMessage())));
        }
    }
}
