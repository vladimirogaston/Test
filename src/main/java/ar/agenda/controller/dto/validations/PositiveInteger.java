package ar.agenda.controller.dto.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositiveIntegerValidator.class)
public @interface PositiveInteger {
    String message() default "Expected positive integer.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
