package br.com.irrah.testetecnico.mensagem;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PlataformaEnvio {
    SMS("SMS"), WHATSAPP("WHATSAPP");

    private final String descricao;

    @Nullable
    public static PlataformaEnvio getPorDescricao(String descricao) {
        return Stream.of(values())
                .filter(plataformaEnvio -> plataformaEnvio.descricao.equals(descricao))
                .findFirst()
                .orElse(null);
    }
}
