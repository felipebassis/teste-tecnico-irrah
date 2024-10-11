package br.com.irrah.testetecnico.backoffice.cliente.usecase;

import br.com.irrah.testetecnico.backoffice.cliente.controller.DadosClienteDTO;
import br.com.irrah.testetecnico.backoffice.cliente.controller.InformacaoPlanoDTO;
import br.com.irrah.testetecnico.backoffice.cliente.entity.ClienteRepository;
import br.com.irrah.testetecnico.backoffice.cliente.entity.TipoPlano;
import br.com.irrah.testetecnico.backoffice.mensagem.entity.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultarClienteUseCase {

    private final BigDecimal custoPorMensagem = BigDecimal.valueOf(0.25);

    private final ClienteRepository clienteRepository;

    private final MensagemRepository mensagemRepository;

    private final DadosClienteDTOMapper mapper;

    public DadosClienteDTO consultarCliente(UUID id) {
        return clienteRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
    }

    public InformacaoPlanoDTO consultarPlano(UUID id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        var plano = cliente.getPlano();
        if (plano.getTipoPlano().equals(TipoPlano.POS_PAGO)) {
            return new InformacaoPlanoDTO(
                    cliente.getId(),
                    plano.getTipoPlano(),
                    plano.getCredito(),
                    plano.getLimiteConsumo(),
                    custoPorMensagem.multiply(BigDecimal.valueOf(mensagemRepository.countByPeriodoAndCliente(YearMonth.now().atDay(1).atStartOfDay(), YearMonth.now().atEndOfMonth().atTime(23, 59, 59), cliente.getId()))));
        }
        return new InformacaoPlanoDTO(
                cliente.getId(),
                plano.getTipoPlano(),
                plano.getCredito(),
                null,
                null
        );
    }
}
