package dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ConstraintViolationResponse {
    private final List<ConstraintViolationImpl> violations = new ArrayList<>();

    private ConstraintViolationResponse(ConstraintViolationException e) {
        e.getConstraintViolations().forEach(v -> violations.add(
                new ConstraintViolationImpl(v.getMessage(),v.getPropertyPath().toString().split("\\.")
                        [v.getPropertyPath().toString().split("\\.").length-1]

                        )
        ));
    }

    private String getAttribute(ConstraintViolation<?> violation){
        Stream.of(violation).forEach();
    }

    public static ConstraintViolationResponse of(ConstraintViolationException exception) {
        return new ConstraintViolationResponse(exception);
    }

    public List<ConstraintViolationImpl> getViolations() {
        return violations;
    }
}
