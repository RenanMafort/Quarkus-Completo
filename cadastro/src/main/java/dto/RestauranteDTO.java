package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RestauranteDTO {
    @NotNull
    @NotBlank
    public String proprietario;

    public String cnpj;
    @Size(min = 3, max = 30,message = "O nomeFantasia deve ter entre 3 e 30 caracteres")
    public String nomeFantasia;
    public LocalizacaoDTO localizacao;
    public String dataCriacao;


}
