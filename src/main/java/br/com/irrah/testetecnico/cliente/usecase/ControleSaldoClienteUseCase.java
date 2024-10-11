package br.com.irrah.testetecnico.cliente.usecase;

import br.com.irrah.testetecnico.cliente.entity.ClienteRepository;
import br.com.irrah.testetecnico.cliente.entity.TipoPlano;
import br.com.irrah.testetecnico.mensagem.entity.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ControleSaldoClienteUseCase {

    private final ClienteRepository clienteRepository;

    private final MensagemRepository mensagemRepository;

    private final BigDecimal custoPorMensagem = BigDecimal.valueOf(0.25);

    @Transactional
    public void debitarCredito(UUID idCliente, Long quantidadeMensagens) {
        var cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        var plano = cliente.getPlano();
        if (plano.getTipoPlano() == TipoPlano.PRE_PAGO) {
            var novoSaldo = plano.getCredito().subtract(custoPorMensagem.multiply(BigDecimal.valueOf(quantidadeMensagens)));
            if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalStateException("Saldo insuficiente.");
            }
            clienteRepository.deduzirSaldo(custoPorMensagem, quantidadeMensagens, idCliente);
        } else {
            var quantidadeMensagensEnviadas = mensagemRepository.countByPeriodoAndCliente(YearMonth.now().atDay(1).atStartOfDay(), YearMonth.now().atEndOfMonth().atTime(23, 59, 59), idCliente);
            if (plano.getLimiteConsumo()
                    .subtract(custoPorMensagem.multiply(BigDecimal.valueOf(quantidadeMensagensEnviadas)))
                    .compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalStateException("Saldo insuficiente.");
            }
        }
    }

    @Transactional
    public void reembolsarSaldo(UUID id) {
        clienteRepository.reembolsarSaldo(custoPorMensagem, id);
    }
}
