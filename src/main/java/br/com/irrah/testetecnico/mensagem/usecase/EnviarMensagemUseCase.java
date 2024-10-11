package br.com.irrah.testetecnico.mensagem.usecase;

import br.com.irrah.testetecnico.cliente.entity.ClienteRepository;
import br.com.irrah.testetecnico.cliente.entity.Telefone;
import br.com.irrah.testetecnico.cliente.usecase.ControleSaldoClienteUseCase;
import br.com.irrah.testetecnico.mensagem.controller.EnvioMensagemDTO;
import br.com.irrah.testetecnico.mensagem.entity.Mensagem;
import br.com.irrah.testetecnico.mensagem.entity.MensagemRepository;
import br.com.irrah.testetecnico.mensagem.entity.StatusMensagem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnviarMensagemUseCase {

    private final MensagemRepository mensagemRepository;

    private final ClienteRepository clienteRepository;

    private final ControleSaldoClienteUseCase controleSaldoClienteUseCase;

    private final EnfileirarEnvioMensagemUseCase enfileirarEnvioMensagemUseCase;

    public void enviarMensagem(UUID idCliente, EnvioMensagemDTO envioMensagemDTO) {
        controleSaldoClienteUseCase.debitarCredito(idCliente, 1L);

        var cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));


        var mensagem = new Mensagem();
        mensagem.setPlataforma(envioMensagemDTO.plataforma());
        mensagem.setTexto(envioMensagemDTO.texto());
        mensagem.setNumeroTelefone(new Telefone(
                envioMensagemDTO.telefone().ddi(),
                envioMensagemDTO.telefone().ddd(),
                envioMensagemDTO.telefone().numero()
        ));
        mensagem.setCliente(cliente);
        mensagem = mensagemRepository.save(mensagem);

        enfileirarEnvioMensagemUseCase.enfileirarMensagem(
                mensagem.getId(),
                cliente.getId(),
                mensagem.getNumeroTelefone(),
                mensagem.getPlataforma(),
                mensagem.getTexto()
        );
    }

    @Transactional
    public void atualizarStatusParaEnviado(UUID id) {
        var mensagemOp = mensagemRepository.findById(id);
        if (mensagemOp.isPresent()) {
            var mensagem = mensagemOp.get();
            mensagem.setStatus(StatusMensagem.ENVIADA);
            mensagemRepository.save(mensagem);
        }
    }

    @Transactional
    public void reembolsarSaldo(UUID id) {
        var mensagemOp = mensagemRepository.findById(id);
        if (mensagemOp.isPresent()) {
            var mensagem = mensagemOp.get();
            mensagem.setStatus(StatusMensagem.FALHA);
            controleSaldoClienteUseCase.reembolsarSaldo(mensagem.getCliente().getId());
            mensagemRepository.save(mensagem);
        }
    }
}
