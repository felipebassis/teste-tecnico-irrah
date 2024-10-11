package br.com.irrah.testetecnico.backoffice.cliente.entity;

import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.MessageFormat;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {

    private static MessageFormat FORMATO_TELEFONE = new MessageFormat("{0} {1} {2}");

    private String ddi;

    private String ddd;

    private String numero;

    private Telefone(String[] dadosFormatoBanco) {
        this.ddi = dadosFormatoBanco[0];
        this.ddd = dadosFormatoBanco[1];
        this.numero = dadosFormatoBanco[2];
    }

    public String formatar() {
        return FORMATO_TELEFONE.format(new Object[]{this.ddi, this.ddd, this.numero});
    }

    public static class TelefoneConverter implements AttributeConverter<Telefone, String> {

        @Override
        public String convertToDatabaseColumn(Telefone attribute) {
            return FORMATO_TELEFONE.format(new Object[]{attribute.getDdi(), attribute.getDdd(), attribute.getNumero()});
        }

        @Override
        public Telefone convertToEntityAttribute(String dbData) {
            return new Telefone(dbData.split(" "));
        }
    }
}
