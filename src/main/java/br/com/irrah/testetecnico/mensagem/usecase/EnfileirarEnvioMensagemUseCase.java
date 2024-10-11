package br.com.irrah.testetecnico.mensagem.usecase;

import br.com.irrah.testetecnico.cliente.entity.Telefone;
import br.com.irrah.testetecnico.mensagem.entity.PlataformaEnvio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class EnfileirarEnvioMensagemUseCase {

    private static final String QUEUE = "enviar_mensagem_queue";

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    void enfileirarMensagem(UUID idMensagem, UUID idCliente, Telefone telefone, PlataformaEnvio plataformaEnvio, String texto) {
        var mensagem = new MensagemQueueMessage(idMensagem, idCliente, telefone.formatar(), plataformaEnvio.getPlataforma(), texto);
        try {
            jmsTemplate.convertAndSend(QUEUE, objectMapper.writeValueAsString(mensagem));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Erro ao enfileirar envio.", e);
        }
    }
}
