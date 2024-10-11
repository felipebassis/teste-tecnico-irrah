package br.com.irrah.testetecnico.mensagem.sms;

import br.com.irrah.testetecnico.mensagem.EnviarMensagemUseCase;
import br.com.irrah.testetecnico.mensagem.EnvioMensagemException;
import br.com.irrah.testetecnico.mensagem.MensagemQueueMessage;
import br.com.irrah.testetecnico.mensagem.PlataformaEnvio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnviarMensagemSmsUseCase implements EnviarMensagemUseCase {

    @Override
    public void enviarMensagem(MensagemQueueMessage mensagem) throws EnvioMensagemException {
        log.info("Enviando mensagem com sucesso por SMS para o numero {}. Texto: {}", mensagem.telefone(), mensagem.texto());
    }

    @Override
    public PlataformaEnvio getPlataforma() {
        return PlataformaEnvio.SMS;
    }
}
