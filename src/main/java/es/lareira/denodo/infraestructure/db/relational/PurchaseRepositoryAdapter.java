package es.lareira.denodo.infraestructure.db.relational;

import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.application.ports.output.repository.PurchaseRepository;
import es.lareira.denodo.infraestructure.db.relational.jpa.JpaPurchaseRepository;
import es.lareira.denodo.infraestructure.db.relational.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseRepositoryAdapter implements PurchaseRepository {
  private final JpaPurchaseRepository jpaPurchaseRepository;
  private final PurchaseMapper purchaseMapper;

  @Override
  public Page<Purchase> getPurchasesDetails(
      PurchaseDetailRequest purchaseDetailRequest, Pageable pageable) {
    return jpaPurchaseRepository
        .getPurchaseDetails(purchaseDetailRequest, pageable)
        .map(purchaseMapper::toDomain);
  }
}
