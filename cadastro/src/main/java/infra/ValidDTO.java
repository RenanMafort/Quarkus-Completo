package infra;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDTOValidator.class)
@Documented
public @interface ValidDTO {
    String message() default "{com.github.viniciusfcf.ifood.cadastro.dto.ValidDTO.message}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
