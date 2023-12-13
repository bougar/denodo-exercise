package es.lareira.denodo.infraestructure.db.relational.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class UserEntity {
  @Id
  @Column(name = "USER_ID")
  private Long id;

  @Column(name = "AGE")
  private Integer age;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<PurchaseEntity> purchases;
}
