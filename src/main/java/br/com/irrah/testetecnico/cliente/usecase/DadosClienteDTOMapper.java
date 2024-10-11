package br.com.irrah.testetecnico.cliente.usecase;

import br.com.irrah.testetecnico.cliente.controller.DadosClienteDTO;
import br.com.irrah.testetecnico.cliente.controller.TelefoneDTO;
import br.com.irrah.testetecnico.cliente.entity.Cliente;
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
                ),
                cliente.getPlano().getTipoPlano(),
                cliente.getPlano().getCredito(),
                cliente.getPlano().getLimiteConsumo()
        );
    }
}
