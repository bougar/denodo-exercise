package es.lareira.denodo.application.domain.validators;

import es.lareira.denodo.application.domain.model.requests.DateRangeRequest;
import es.lareira.denodo.application.domain.validators.aspects.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, DateRangeRequest> {
  @Override
  public boolean isValid(DateRangeRequest value, ConstraintValidatorContext context) {
    return value.getStartDate().isBefore(value.getEndDate());
  }
}
