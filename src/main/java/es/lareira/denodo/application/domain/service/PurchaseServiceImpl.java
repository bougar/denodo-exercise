package es.lareira.denodo.application.domain.service;

import es.lareira.denodo.application.domain.model.exceptions.NoPurchasesFoundException;
import es.lareira.denodo.application.domain.model.purchase.AgeRange;
import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.DateRangeRequest;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.application.ports.input.service.PurchaseService;
import es.lareira.denodo.application.ports.output.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {
  private final PurchaseRepository purchaseRepository;

  @Override
  public Page<Purchase> getPurchasesDetails(
      PurchaseDetailRequest purchaseDetailRequest, Pageable pageable) {
    return purchaseRepository.getPurchasesDetails(purchaseDetailRequest, pageable);
  }

  @Override
  public AgeRange getBuyersAgeRange(DateRangeRequest dateRangeRequest) {
    return purchaseRepository.getBuyersAges(dateRangeRequest)
            .stream()
            .map(AgeRange::getAgeRange)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElseThrow(NoPurchasesFoundException::new);
  }
}
