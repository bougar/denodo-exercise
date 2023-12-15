package es.lareira.denodo.infraestructure.db.relational;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.application.domain.model.requests.DateRangeRequest;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.config.QueryConfig;
import es.lareira.denodo.infraestructure.db.relational.entity.PurchaseEntity;
import es.lareira.denodo.infraestructure.db.relational.jpa.JpaPurchaseRepository;
import es.lareira.denodo.infraestructure.db.relational.mapper.PurchaseMapper;
import java.time.LocalDateTime;
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
class PurchaseRepositoryAdapterTest {
  @Mock private JpaPurchaseRepository jpaPurchaseRepository;
  @Mock private PurchaseMapper purchaseMapper;
  @Mock private QueryConfig queryConfig;
  @InjectMocks private PurchaseRepositoryAdapter purchaseRepositoryAdapter;

  @Test
  void when_repository_returns_empty_page_then_return_empty_page() {
    PurchaseDetailRequest purchaseDetailRequest =
        PurchaseDetailRequest.builder().totalAmount(100.23).userId(2L).build();
    Pageable pageable = PageRequest.of(0, 10);
    when(jpaPurchaseRepository.getPurchaseDetails(purchaseDetailRequest, pageable))
        .thenReturn(Page.empty());
    Page<Purchase> result =
        purchaseRepositoryAdapter.getPurchasesDetails(purchaseDetailRequest, pageable);
    verifyNoInteractions(purchaseMapper);
    assertTrue(result.isEmpty());
  }

  @Test
  void when_repository_returns_page_then_result_is_mapped() {
    PurchaseDetailRequest purchaseDetailRequest =
        PurchaseDetailRequest.builder().totalAmount(100.23).userId(2L).build();
    Pageable pageable = PageRequest.of(0, 10);
    PurchaseEntity savedEntity = PurchaseEntity.builder().id(1L).build();
    when(jpaPurchaseRepository.getPurchaseDetails(purchaseDetailRequest, pageable))
        .thenReturn(new PageImpl<>(List.of(savedEntity)));
    when(purchaseMapper.toDomain(savedEntity)).thenReturn(Purchase.builder().id(1L).build());
    Page<Purchase> result =
        purchaseRepositoryAdapter.getPurchasesDetails(purchaseDetailRequest, pageable);
    assertFalse(result.isEmpty());
    assertEquals(1, result.getContent().get(0).getId());
  }

  @Test
  void when_repository_returns_page_list_then_both_mapped() {
    PurchaseDetailRequest purchaseDetailRequest =
        PurchaseDetailRequest.builder().totalAmount(100.23).userId(2L).build();
    Pageable pageable = PageRequest.of(0, 10);
    PurchaseEntity savedEntity1 = PurchaseEntity.builder().id(2L).build();
    PurchaseEntity savedEntity2 = PurchaseEntity.builder().id(7L).build();
    when(jpaPurchaseRepository.getPurchaseDetails(purchaseDetailRequest, pageable))
        .thenReturn(new PageImpl<>(List.of(savedEntity1, savedEntity2)));
    when(purchaseMapper.toDomain(savedEntity1)).thenReturn(Purchase.builder().id(2L).build());
    when(purchaseMapper.toDomain(savedEntity2)).thenReturn(Purchase.builder().id(7L).build());
    Page<Purchase> result =
        purchaseRepositoryAdapter.getPurchasesDetails(purchaseDetailRequest, pageable);
    assertFalse(result.isEmpty());
    assertEquals(2, result.getContent().get(0).getId());
    assertEquals(7, result.getContent().get(1).getId());
  }

  @Test
  void when_get_buyers_ages_then_call_repository() {
    DateRangeRequest dateRangeRequest = DateRangeRequest.builder()
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .build();
    when(queryConfig.getMinimumFrequencyTotalAmount()).thenReturn(100);
    purchaseRepositoryAdapter.getBuyersAges(dateRangeRequest);
    verify(jpaPurchaseRepository).getBuyersAges(dateRangeRequest, 100);
  }
}
