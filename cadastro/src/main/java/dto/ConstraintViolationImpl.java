package dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

public record ConstraintViolationImpl(
        @Schema(description = "Path do atributo, ex: dataInicio, pessoa.endereco.numero") String atributo,
        @Schema(description = "Mensagem descritiva do erro possivelmente associado ao path", required = true) String mensagem)
        implements Serializable {

    public ConstraintViolationImpl(String atributo, String mensagem) {
        this.atributo = atributo;
        this.mensagem = mensagem;
    }

}
