package br.com.irrah.testetecnico.backoffice.cliente.usecase;

import br.com.irrah.testetecnico.backoffice.cliente.controller.ModificarPlanoDTO;
import br.com.irrah.testetecnico.backoffice.cliente.entity.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModificarPlanoUseCase {

    private final ClienteRepository clienteRepository;

    @Transactional
    public void modificarPlano(UUID id, ModificarPlanoDTO modificarPlanoDTO) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        var plano = cliente.getPlano();
        plano.setTipoPlano(modificarPlanoDTO.tipoPlano());
        plano.setLimiteConsumo(modificarPlanoDTO.novoLimite());
        plano.setCredito(modificarPlanoDTO.novoSaldo());

        clienteRepository.save(cliente);
    }
}
