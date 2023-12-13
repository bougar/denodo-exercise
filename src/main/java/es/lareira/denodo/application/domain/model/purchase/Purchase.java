package es.lareira.denodo.application.domain.model.purchase;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Purchase {
  private Long id;
  private User user;
  private LocalDateTime purchaseDate;
  private Double totalAmount;
}
