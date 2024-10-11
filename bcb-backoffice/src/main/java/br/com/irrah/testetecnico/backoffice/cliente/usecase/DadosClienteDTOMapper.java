package br.com.irrah.testetecnico.backoffice.cliente.usecase;

import br.com.irrah.testetecnico.backoffice.cliente.controller.DadosClienteDTO;
import br.com.irrah.testetecnico.backoffice.cliente.controller.TelefoneDTO;
import br.com.irrah.testetecnico.backoffice.cliente.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
class DadosClienteDTOMapper {

    DadosClienteDTO toDTO(Cliente cliente) {
        return new DadosClienteDTO(
                cliente.getId(),
                cliente.getEmail(),
                cliente.getNome(),
                cliente.getDocumento(),
                cliente.getDocumentoResponsavel(),
                new TelefoneDTO(
                        cliente.getTelefone().getDdi(),
                        cliente.getTelefone().getDdd(),
                        cliente.getTelefone().getNumero()
                )
        );
    }
}
