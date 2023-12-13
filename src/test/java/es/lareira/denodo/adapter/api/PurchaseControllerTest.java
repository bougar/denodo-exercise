package es.lareira.denodo.adapter.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import es.lareira.denodo.adapter.api.mapper.AgeMapper;
import es.lareira.denodo.adapter.api.mapper.PurchaseDTOMapper;
import es.lareira.denodo.application.domain.model.purchase.AgeRange;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.application.ports.input.service.PurchaseService;
import es.lareira.denodo.generated.model.AgeRangeDTO;
import es.lareira.denodo.generated.model.GetPurchasesPageableParameterDTO;
import es.lareira.denodo.generated.model.PaginatedPurchasesDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class PurchaseControllerTest {
  @Mock private PurchaseService purchaseService;
  @Mock private PurchaseDTOMapper purchaseDTOMapper;
  @Mock private AgeMapper ageMapper;
  @InjectMocks
  private PurchaseController purchaseController;

  @Test
  void when_get_purchase_without_params_then_call_service_with_null_params_on_request() {
    when(purchaseService.getPurchasesDetails(any(), any())).thenReturn(Page.empty());
    verifyNoInteractions(purchaseDTOMapper);
    ArgumentCaptor<PurchaseDetailRequest> requestCaptor = ArgumentCaptor.forClass(PurchaseDetailRequest.class);
    GetPurchasesPageableParameterDTO page = new GetPurchasesPageableParameterDTO()
            .page(0)
            .size(10);
    when(purchaseService.getPurchasesDetails(any(), any())).thenReturn(Page.empty());
    when(purchaseDTOMapper.toPaginatedDto(any())).thenReturn(new PaginatedPurchasesDTO());
    PaginatedPurchasesDTO result = purchaseController.getPurchases(null, null, page);
    ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
    verify(purchaseService).getPurchasesDetails(requestCaptor.capture(), pageableCaptor.capture());
    assertNotNull(requestCaptor.getValue());
    assertNull(requestCaptor.getValue().getUserId());
    assertNull(requestCaptor.getValue().getTotalAmount());
    assertEquals(0, pageableCaptor.getValue().getPageNumber());
    assertEquals(10, pageableCaptor.getValue().getPageSize());
    assertNotNull(result);
  }

  @Test
  void when_get_purchase_wit_params_then_call_service_with_correct_params() {
    when(purchaseService.getPurchasesDetails(any(), any())).thenReturn(Page.empty());
    verifyNoInteractions(purchaseDTOMapper);
    ArgumentCaptor<PurchaseDetailRequest> requestCaptor = ArgumentCaptor.forClass(PurchaseDetailRequest.class);
    GetPurchasesPageableParameterDTO page = new GetPurchasesPageableParameterDTO()
            .page(7)
            .size(20);
    when(purchaseService.getPurchasesDetails(any(), any())).thenReturn(Page.empty());
    when(purchaseDTOMapper.toPaginatedDto(any())).thenReturn(new PaginatedPurchasesDTO());
    PaginatedPurchasesDTO result = purchaseController.getPurchases(15, BigDecimal.ONE, page);
    ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
    verify(purchaseService).getPurchasesDetails(requestCaptor.capture(), pageableCaptor.capture());
    assertNotNull(requestCaptor.getValue());
    assertEquals(15, requestCaptor.getValue().getUserId());
    assertEquals(1, requestCaptor.getValue().getTotalAmount());
    assertEquals(7, pageableCaptor.getValue().getPageNumber());
    assertEquals(20, pageableCaptor.getValue().getPageSize());
    assertNotNull(result);
  }

  @Test
  void when_get_more_frequent_age_range_then_return_age_range() {
    AgeRange ageRange = AgeRange.AGE_RANGE_35_44;
    when(purchaseService.getBuyersAgeRange(any())).thenReturn(ageRange);
    when(ageMapper.toDto(ageRange)).thenReturn(new AgeRangeDTO());
    AgeRangeDTO result = purchaseController.getMoreFrequentAgeRange(LocalDateTime.MIN, LocalDateTime.MAX);
    assertNotNull(result);
  }
}
