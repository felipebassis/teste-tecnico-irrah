package br.com.irrah.testetecnico.backoffice.mensagem.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PlataformaEnvio {
    SMS("SMS"), WHATSAPP("WHATSAPP"),;

    private final String plataforma;
}
