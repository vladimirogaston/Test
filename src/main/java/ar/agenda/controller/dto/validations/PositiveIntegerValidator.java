package ar.agenda.controller.dto.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveIntegerValidator implements ConstraintValidator<PositiveInteger, Integer> {

    @Override
    public void initialize(PositiveInteger constraintAnnotation) {
        // Empty, not operation
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value > 0;
    }
}
