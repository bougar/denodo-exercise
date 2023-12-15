package es.lareira.denodo.infraestructure.db.relational;

import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.DateRangeRequest;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.application.ports.output.repository.PurchaseRepository;
import es.lareira.denodo.config.QueryConfig;
import es.lareira.denodo.infraestructure.db.relational.jpa.JpaPurchaseRepository;
import es.lareira.denodo.infraestructure.db.relational.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PurchaseRepositoryAdapter implements PurchaseRepository {
  private final JpaPurchaseRepository jpaPurchaseRepository;
  private final PurchaseMapper purchaseMapper;
  private final QueryConfig queryConfig;

  @Override
  public Page<Purchase> getPurchasesDetails(
      PurchaseDetailRequest purchaseDetailRequest, Pageable pageable) {
    return jpaPurchaseRepository
        .getPurchaseDetails(purchaseDetailRequest, pageable)
        .map(purchaseMapper::toDomain);
  }

  @Override
  public List<Integer> getBuyersAges(DateRangeRequest dateRangeRequest) {
    return jpaPurchaseRepository.getBuyersAges(
        dateRangeRequest, queryConfig.getMinimumFrequencyTotalAmount());
  }
}
