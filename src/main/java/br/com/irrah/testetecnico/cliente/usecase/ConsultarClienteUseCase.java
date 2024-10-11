package br.com.irrah.testetecnico.cliente.usecase;

import br.com.irrah.testetecnico.cliente.controller.DadosClienteDTO;
import br.com.irrah.testetecnico.cliente.entity.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultarClienteUseCase {

    private final ClienteRepository clienteRepository;

    private final DadosClienteDTOMapper mapper;

    public DadosClienteDTO consultarCliente(UUID id) {
        return clienteRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
    }
}
