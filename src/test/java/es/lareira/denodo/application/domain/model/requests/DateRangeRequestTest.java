package es.lareira.denodo.application.domain.model.requests;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateRangeRequestTest {
  @Test
  void when_start_date_null_then_return_min() {
    DateRangeRequest dateRangeRequest = DateRangeRequest.builder().startDate(null).build();
    assertEquals(LocalDateTime.MIN, dateRangeRequest.getStartDate());
  }

  @Test
  void when_end_date_null_then_return_not_null() {
    DateRangeRequest dateRangeRequest = DateRangeRequest.builder().endDate(null).build();
    assertNotNull(dateRangeRequest.getEndDate());
  }

  @Test
  void when_start_date_not_null_then_return_start_date() {
    LocalDateTime time = LocalDateTime.now();
    DateRangeRequest dateRangeRequest = DateRangeRequest.builder().startDate(time).build();
    assertEquals(time, dateRangeRequest.getStartDate());
  }

  @Test
  void when_end_date_not_null_then_return_end_date() {
    LocalDateTime time = LocalDateTime.now();
    DateRangeRequest dateRangeRequest = DateRangeRequest.builder().endDate(time).build();
    assertEquals(time, dateRangeRequest.getEndDate());
  }
}
