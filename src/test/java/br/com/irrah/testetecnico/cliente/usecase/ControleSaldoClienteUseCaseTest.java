package br.com.irrah.testetecnico.cliente.usecase;

import br.com.irrah.testetecnico.cliente.entity.Cliente;
import br.com.irrah.testetecnico.cliente.entity.ClienteRepository;
import br.com.irrah.testetecnico.cliente.entity.InformacoesPlano;
import br.com.irrah.testetecnico.cliente.entity.TipoPlano;
import br.com.irrah.testetecnico.mensagem.entity.MensagemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ControleSaldoClienteUseCaseTest {

    private ControleSaldoClienteUseCase controleSaldoClienteUseCase;

    private ClienteRepository clienteRepository;

    private MensagemRepository mensagemRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        this.clienteRepository = mock(ClienteRepository.class);
        this.mensagemRepository = mock(MensagemRepository.class);
        this.cliente = mock(Cliente.class);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        this.controleSaldoClienteUseCase = new ControleSaldoClienteUseCase(clienteRepository, mensagemRepository);
    }

    @Test
    void deveValidarCorretamenteOSaldoPrePago() {
        var plano = mock(InformacoesPlano.class);
        when(cliente.getPlano()).thenReturn(plano);
        when(plano.getTipoPlano()).thenReturn(TipoPlano.PRE_PAGO);
        when(plano.getCredito()).thenReturn(BigDecimal.ONE);
        var idCliente = UUID.randomUUID();
        assertDoesNotThrow(() -> controleSaldoClienteUseCase.debitarCredito(idCliente, 2L));
        verify(clienteRepository, times(1)).deduzirSaldo(BigDecimal.valueOf(0.25), 2L, idCliente);
    }

    @Test
    void deveRetornarErroQuandoSaldoPrePagoForInsuficiente() {
        var plano = mock(InformacoesPlano.class);
        when(cliente.getPlano()).thenReturn(plano);
        when(plano.getTipoPlano()).thenReturn(TipoPlano.PRE_PAGO);
        when(plano.getCredito()).thenReturn(BigDecimal.ONE);
        var idCliente = UUID.randomUUID();
        var exception = assertThrows(IllegalStateException.class, () -> controleSaldoClienteUseCase.debitarCredito(idCliente, 5L));
        assertEquals("Saldo insuficiente.", exception.getMessage());
        verify(clienteRepository, never()).deduzirSaldo(any(), any(), any());
    }

    @Test
    void deveValidarCorretamenteOLimitePosPago() {
        var plano = mock(InformacoesPlano.class);
        when(cliente.getPlano()).thenReturn(plano);
        when(plano.getTipoPlano()).thenReturn(TipoPlano.POS_PAGO);
        when(plano.getLimiteConsumo()).thenReturn(BigDecimal.ONE);
        var idCliente = UUID.randomUUID();
        when(mensagemRepository.countByPeriodoAndCliente(
                YearMonth.now()
                        .atDay(1)
                        .atStartOfDay(),
                YearMonth.now()
                        .atEndOfMonth()
                        .atTime(23, 59, 59),
                idCliente)
        ).thenReturn(1L);
        assertDoesNotThrow(() -> controleSaldoClienteUseCase.debitarCredito(idCliente, 1L));
    }

    @Test
    void deveRetornarErroAoNaoPossuirLimitePosPago() {
        var plano = mock(InformacoesPlano.class);
        when(cliente.getPlano()).thenReturn(plano);
        when(plano.getTipoPlano()).thenReturn(TipoPlano.POS_PAGO);
        when(plano.getLimiteConsumo()).thenReturn(BigDecimal.valueOf(0.25));
        var idCliente = UUID.randomUUID();
        when(mensagemRepository.countByPeriodoAndCliente(
                YearMonth.now()
                        .atDay(1)
                        .atStartOfDay(),
                YearMonth.now()
                        .atEndOfMonth()
                        .atTime(23, 59, 59),
                idCliente)
        ).thenReturn(2L);
        var exception = assertThrows(IllegalStateException.class, () -> controleSaldoClienteUseCase.debitarCredito(idCliente, 1L));
        assertEquals("Saldo insuficiente.", exception.getMessage());
    }
}