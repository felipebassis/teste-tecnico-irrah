package br.com.irrah.testetecnico.cliente.controller;

import br.com.irrah.testetecnico.cliente.usecase.ConsultarClienteUseCase;
import br.com.irrah.testetecnico.cliente.usecase.ModificarClienteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/clientes/{id}")
class ClienteController {

    private final ConsultarClienteUseCase consultarClienteUseCase;

    private final ModificarClienteUseCase modificarClienteUseCase;

    @GetMapping
    DadosClienteDTO buscarDados(@PathVariable("id") UUID id) {
        return consultarClienteUseCase.consultarCliente(id);
    }

    @PutMapping
    DadosClienteDTO atualizarDados(@PathVariable("id") UUID id,
                                   @RequestBody @Valid AtualizarDadosClienteDTO atualizarDadosClienteDTO) {
        return modificarClienteUseCase.modificarCliente(id, atualizarDadosClienteDTO);
    }
}
