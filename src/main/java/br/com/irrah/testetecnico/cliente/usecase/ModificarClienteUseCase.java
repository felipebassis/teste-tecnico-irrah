package br.com.irrah.testetecnico.cliente.usecase;

import br.com.irrah.testetecnico.cliente.controller.AtualizarDadosClienteDTO;
import br.com.irrah.testetecnico.cliente.controller.DadosClienteDTO;
import br.com.irrah.testetecnico.cliente.entity.ClienteRepository;
import br.com.irrah.testetecnico.cliente.entity.Telefone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModificarClienteUseCase {

    private final ClienteRepository clienteRepository;

    private final DadosClienteDTOMapper mapper;

    @Transactional
    public DadosClienteDTO modificarCliente(UUID id, AtualizarDadosClienteDTO atualizarDadosClienteDTO) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        cliente.setNome(atualizarDadosClienteDTO.nome());
        cliente.setEmail(atualizarDadosClienteDTO.email());
        cliente.setDocumentoResponsavel(atualizarDadosClienteDTO.documentoResponsavel());
        cliente.setTelefone(new Telefone(
                atualizarDadosClienteDTO.telefone().ddi(),
                atualizarDadosClienteDTO.telefone().ddd(),
                atualizarDadosClienteDTO.telefone().numero()
        ));
        clienteRepository.save(cliente);

        return mapper.toDTO(cliente);
    }
}
