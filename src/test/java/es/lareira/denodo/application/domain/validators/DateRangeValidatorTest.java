package es.lareira.denodo.application.domain.validators;

import static org.junit.jupiter.api.Assertions.*;

import es.lareira.denodo.application.domain.model.requests.DateRangeRequest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DateRangeValidatorTest {
  @InjectMocks private DateRangeValidator dateRangeValidator;

  @Test
  void when_start_date_is_minor_than_end_date_then_return_true() {
    DateRangeRequest dateRequest =
        DateRangeRequest.builder()
            .startDate(LocalDateTime.of(2020, 1, 1, 0, 0))
            .endDate(LocalDateTime.of(2020, 1, 2, 0, 0))
            .build();
    assertTrue(dateRangeValidator.isValid(dateRequest, null));
  }

  @Test
  void when_start_date_is_minor_than_end_date_then_return_false() {
    DateRangeRequest dateRequest =
        DateRangeRequest.builder()
            .startDate(LocalDateTime.of(2020, 1, 2, 0, 0))
            .endDate(LocalDateTime.of(2020, 1, 1, 0, 0))
            .build();
    assertFalse(dateRangeValidator.isValid(dateRequest, null));
  }
}
