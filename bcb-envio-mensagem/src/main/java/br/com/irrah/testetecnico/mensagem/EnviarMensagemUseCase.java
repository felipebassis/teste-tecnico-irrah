package br.com.irrah.testetecnico.mensagem;

public interface EnviarMensagemUseCase {

    void enviarMensagem(MensagemQueueMessage mensagem) throws EnvioMensagemException;

    PlataformaEnvio getPlataforma();
}
