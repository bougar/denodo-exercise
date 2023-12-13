package es.lareira.denodo.adapter.api.mapper;

import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.generated.model.PaginatedPurchasesDTO;
import es.lareira.denodo.generated.model.PurchaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(
    componentModel = "spring",
    uses = {UserDTOMapper.class})
public interface PurchaseDTOMapper {
  @Mapping(target = "total", source = "totalAmount")
  @Mapping(target = "date", source = "purchaseDate")
  PurchaseDTO toDto(Purchase purchase);

  @Mapping(target = "page", source = "number")
  PaginatedPurchasesDTO toPaginatedDto(Page<Purchase> purchasesDetails);
}
