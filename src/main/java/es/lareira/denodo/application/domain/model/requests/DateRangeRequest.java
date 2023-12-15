package es.lareira.denodo.application.domain.model.requests;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.commons.lang3.ObjectUtils;

@Builder
@AllArgsConstructor
public class DateRangeRequest {
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  public LocalDateTime getStartDate() {
    return ObjectUtils.firstNonNull(startDate, LocalDateTime.MIN);
  }

  public LocalDateTime getEndDate() {
    return ObjectUtils.firstNonNull(endDate, LocalDateTime.now());
  }
}
