package es.lareira.denodo.application.ports.output.repository;

import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.DateRangeRequest;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseRepository {
  Page<Purchase> getPurchasesDetails(
      PurchaseDetailRequest purchaseDetailRequest, Pageable pageable);

  List<Integer> getBuyersAges(DateRangeRequest dateRangeRequest);
}
