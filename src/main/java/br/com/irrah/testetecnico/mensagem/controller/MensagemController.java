package br.com.irrah.testetecnico.mensagem.controller;

import br.com.irrah.testetecnico.mensagem.usecase.EnviarMensagemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/clientes/{id_cliente}/mensagens")
class MensagemController {

    private final EnviarMensagemUseCase enviarMensagemUseCase;

    @PostMapping
    void enviarMensagem(@PathVariable("id_cliente") UUID idCliente,
                        @RequestBody EnvioMensagemDTO envioMensagemDTO) {
        enviarMensagemUseCase.enviarMensagem(idCliente, envioMensagemDTO);
    }
}
