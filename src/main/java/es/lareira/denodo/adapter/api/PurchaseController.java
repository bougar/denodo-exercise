package es.lareira.denodo.adapter.api;

import es.lareira.denodo.adapter.api.mapper.PurchaseDTOMapper;
import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.application.ports.input.service.PurchaseService;
import es.lareira.denodo.generated.api.PurchasesApi;
import es.lareira.denodo.generated.model.AgeRangeDTO;
import es.lareira.denodo.generated.model.GetPurchasesPageableParameterDTO;
import es.lareira.denodo.generated.model.PaginatedPurchasesDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchaseController implements PurchasesApi {
  private final PurchaseService purchaseService;
  private final PurchaseDTOMapper purchaseDTOMapper;

  @Override
  public AgeRangeDTO getMoreFrequentAgeRange(LocalDateTime from, LocalDateTime to) {
    throw new NotImplementedException("Not implemented yet");
  }

  @Override
  public PaginatedPurchasesDTO getPurchases(
      Integer userId, BigDecimal total, GetPurchasesPageableParameterDTO pageable) {
    PurchaseDetailRequest request =
        PurchaseDetailRequest.builder()
            .userId(userId == null ? null : userId.longValue())
            .totalAmount(total == null ? null : total.doubleValue())
            .build();
    PageRequest pageRequest = PageRequest.of(pageable.getPage(), pageable.getSize());
    Page<Purchase> purchasesDetails = purchaseService.getPurchasesDetails(request, pageRequest);
    return purchaseDTOMapper.toPaginatedDto(purchasesDetails);
  }
}
