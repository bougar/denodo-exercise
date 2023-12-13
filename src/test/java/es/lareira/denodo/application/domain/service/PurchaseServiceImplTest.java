package es.lareira.denodo.application.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.application.ports.output.repository.PurchaseRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {
  @Mock private PurchaseRepository purchaseRepository;

  @InjectMocks private PurchaseServiceImpl purchaseServiceImpl;

  @Test
  void when_getPurchasesDetails_then_returnPageOfPurchases() {
    PurchaseDetailRequest purchaseDetailRequest =
        PurchaseDetailRequest.builder().userId(1L).totalAmount(100.0).build();
    Purchase purchase1 = Purchase.builder().id(1L).totalAmount(100.0).build();
    Purchase purchase2 = Purchase.builder().id(2L).totalAmount(100.0).build();
    Pageable pageable = Pageable.unpaged();
    when(purchaseRepository.getPurchasesDetails(purchaseDetailRequest, pageable))
        .thenReturn(new PageImpl<>(List.of(purchase1, purchase2)));
    Page<Purchase> result = purchaseServiceImpl.getPurchasesDetails(purchaseDetailRequest, pageable);
    assertNotNull(result);
    assertFalse(result.getContent().isEmpty());
    assertEquals(2, result.getContent().size());
    assertEquals(purchase1, result.getContent().get(0));
    assertEquals(purchase2, result.getContent().get(1));
  }

  @Test
  void when_getPurchasesDetails_query_not_existing_user_then_return_empty_page() {
    PurchaseDetailRequest purchaseDetailRequest =
        PurchaseDetailRequest.builder().userId(2L).build();
    Pageable pageable = Pageable.unpaged();
    when(purchaseRepository.getPurchasesDetails(purchaseDetailRequest, pageable)).thenReturn(Page.empty());
    Page<Purchase> result = purchaseServiceImpl.getPurchasesDetails(purchaseDetailRequest, pageable);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  void when_getPurchasesDetails_query_not_existing_amount_user_then_return_empty_page() {
    PurchaseDetailRequest purchaseDetailRequest =
        PurchaseDetailRequest.builder().totalAmount(134.43D).build();
    Pageable pageable = PageRequest.of(0, 10);
    when(purchaseRepository.getPurchasesDetails(purchaseDetailRequest, pageable)).thenReturn(Page.empty());
    Page<Purchase> result = purchaseServiceImpl.getPurchasesDetails(purchaseDetailRequest, pageable);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
