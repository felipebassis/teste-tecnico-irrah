package br.com.irrah.testetecnico.mensagem.controller;

import br.com.irrah.testetecnico.cliente.controller.TelefoneDTO;
import br.com.irrah.testetecnico.mensagem.entity.PlataformaEnvio;

public record EnvioMensagemDTO(
        TelefoneDTO telefone,
        String texto,
        PlataformaEnvio plataforma

) {
}
