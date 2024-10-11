package br.com.irrah.testetecnico.mensagem;

public class EnvioMensagemException extends Exception {

    public EnvioMensagemException(String message) {
        super(message);
    }

    public EnvioMensagemException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnvioMensagemException(Throwable cause) {
        super(cause);
    }
}
