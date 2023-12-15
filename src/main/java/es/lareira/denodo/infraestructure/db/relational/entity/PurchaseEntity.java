package es.lareira.denodo.infraestructure.db.relational.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Purchases")
public class PurchaseEntity {
  @Id
  @Column(name = "PURCHASE_ID")
  private Long id;

  @ManyToOne(targetEntity = UserEntity.class, fetch = jakarta.persistence.FetchType.LAZY)
  @JoinColumn(name = "USER_ID", nullable = false)
  private UserEntity user;

  @Column(name = "TOTAL_AMOUNT")
  private Double totalAmount;

  @Column(name = "PURCHASE_DATE")
  private LocalDateTime purchaseDate;
}
