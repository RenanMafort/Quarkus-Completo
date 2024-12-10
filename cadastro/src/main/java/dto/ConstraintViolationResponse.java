package dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class ConstraintViolationResponse {
    private final List<ConstraintViolationImpl> violations = new ArrayList<>();

    private ConstraintViolationResponse(ConstraintViolationException e) {
        e.getConstraintViolations().forEach(v -> violations.add(
                new ConstraintViolationImpl(getAttribute(v),v.getMessage())
        ));
    }

    private String getAttribute(ConstraintViolation<?> violation){
        String[] split = violation.getPropertyPath().toString().split("\\.");
        return split.length > 0 ? split[split.length -1] : "";
    }

    public static ConstraintViolationResponse of(ConstraintViolationException exception) {
        return new ConstraintViolationResponse(exception);
    }

    public List<ConstraintViolationImpl> getViolations() {
        return violations;
    }
}
