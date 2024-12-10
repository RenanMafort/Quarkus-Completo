package dto;

import jakarta.validation.ConstraintViolation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstraintViolationImpl implements Serializable {

    @Schema(description = "Path do atributo, ex: dataInicio, pessoa.endereco.numero", required = false)
    private final String atributo;

    @Schema(description = "Mensagem descritiva do erro possivelmente associado ao path", required = true)
    private final String mensagem;

    public ConstraintViolationImpl(String atributo, String mensagem) {
        this.atributo = atributo;
        this.mensagem = mensagem;
    }

    public String getAtributo() {
        return atributo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
