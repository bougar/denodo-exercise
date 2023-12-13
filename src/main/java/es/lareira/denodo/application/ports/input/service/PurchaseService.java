package es.lareira.denodo.application.ports.input.service;

import es.lareira.denodo.application.domain.model.purchase.AgeRange;
import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.DateRangeRequest;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
  Page<Purchase> getPurchasesDetails(
      PurchaseDetailRequest purchaseDetailRequest, Pageable pageable);

  AgeRange getBuyersAgeRange(DateRangeRequest dateRangeRequest);
}
