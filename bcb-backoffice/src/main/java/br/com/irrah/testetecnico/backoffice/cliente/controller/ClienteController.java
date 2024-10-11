package br.com.irrah.testetecnico.backoffice.cliente.controller;

import br.com.irrah.testetecnico.backoffice.cliente.usecase.ConsultarClienteUseCase;
import br.com.irrah.testetecnico.backoffice.cliente.usecase.ModificarPlanoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/clientes/{id}")
class ClienteController {

    private final ConsultarClienteUseCase consultarClienteUseCase;

    private final ModificarPlanoUseCase modificarPlanoUseCase;

    @GetMapping
    DadosClienteDTO buscarDados(@PathVariable("id") UUID id) {
        return consultarClienteUseCase.consultarCliente(id);
    }

    @GetMapping("plano")
    InformacaoPlanoDTO consultarPlano(@PathVariable("id") UUID id) {
        return consultarClienteUseCase.consultarPlano(id);
    }

    @PatchMapping("plano")
    void alterarPlano(@PathVariable("id") UUID id,
                      @RequestBody ModificarPlanoDTO modificarPlanoDTO) {
        modificarPlanoUseCase.modificarPlano(id, modificarPlanoDTO);
    }
}
