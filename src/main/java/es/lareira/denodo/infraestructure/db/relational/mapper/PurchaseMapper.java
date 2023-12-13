package es.lareira.denodo.infraestructure.db.relational.mapper;

import es.lareira.denodo.application.domain.model.purchase.Purchase;
import es.lareira.denodo.infraestructure.db.relational.entity.PurchaseEntity;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class})
public interface PurchaseMapper {
  Purchase toDomain(PurchaseEntity purchaseEntity);
}
