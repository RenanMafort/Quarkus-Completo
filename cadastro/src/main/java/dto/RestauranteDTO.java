package dto;

import com.sereia.renan.cadastro.Restaurante;
import infra.DTO;
import infra.ValidDTO;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@ValidDTO
public class RestauranteDTO implements DTO {
    public Long id;
    @NotNull
    @NotBlank
    public String proprietario;
    @Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}-[0-9]{2}", message = "Deve corresponder ao padrão de um CNPJ")
    public String cnpj;
    @Size(min = 3, max = 30,message = "O nomeFantasia deve ter entre 3 e 30 caracteres")
    public String nomeFantasia;
    public LocalizacaoDTO localizacao;
    public String dataCriacao;

    @Override
    public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
       constraintValidatorContext.disableDefaultConstraintViolation();
       if (Restaurante.find("cnpj",cnpj).count()>0){
           constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
                   .addPropertyNode("cnpj")//aqui coloca o atributo que esta sendo validado
                   .addConstraintViolation();
           return false;
       }
       return true;
    }
}
