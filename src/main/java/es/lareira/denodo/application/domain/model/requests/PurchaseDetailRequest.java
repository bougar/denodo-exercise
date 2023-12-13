package es.lareira.denodo.application.domain.model.requests;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseDetailRequest {
  private Long userId;
  private Double totalAmount;
}
