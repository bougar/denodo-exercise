package es.lareira.denodo.infraestructure.db.relational.jpa;

import es.lareira.denodo.application.domain.model.requests.DateRangeRequest;
import es.lareira.denodo.application.domain.model.requests.PurchaseDetailRequest;
import es.lareira.denodo.infraestructure.db.relational.entity.PurchaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
  @Query(
      "SELECT p FROM PurchaseEntity p "
          + "WHERE (:#{#request.userId} IS NULL OR p.user.id = :#{#request.userId}) "
          + "    AND (:#{#request.totalAmount} IS NULL OR p.totalAmount = :#{#request.totalAmount})")
  Page<PurchaseEntity> getPurchaseDetails(
      @Param("request") PurchaseDetailRequest request, Pageable pageable);

  @Query(
      "SELECT p.user.age FROM PurchaseEntity p "
          + "WHERE p.purchaseDate BETWEEN :#{#request.startDate} AND :#{#request.endDate} "
          + "AND p.totalAmount > :minAmount")
  List<Integer> getBuyersAges(
      @Param("request") DateRangeRequest dateRangeRequest, Integer minAmount);
}
