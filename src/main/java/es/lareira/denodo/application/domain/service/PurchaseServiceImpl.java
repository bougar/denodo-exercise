package es.lareira.denodo.application.domain.service;

import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.application.ports.input.service.PurchaseService;
import es.lareira.denodo.application.ports.output.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {
  private final PurchaseRepository purchaseRepository;

  @Override
  public Page<Purchase> getPurchasesDetails(
      PurchaseDetailRequest purchaseDetailRequest, Pageable pageable) {
    return purchaseRepository.getPurchasesDetails(purchaseDetailRequest, pageable);
  }
}
