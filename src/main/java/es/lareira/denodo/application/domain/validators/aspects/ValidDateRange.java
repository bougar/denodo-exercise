package es.lareira.denodo.application.domain.validators.aspects;

import es.lareira.denodo.application.domain.validators.DateRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {

  String message() default "Start date must be before end date";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
