package es.lareira.denodo.adapter.api.mapper;

import es.lareira.denodo.application.domain.model.purchase.AgeRange;
import es.lareira.denodo.generated.model.AgeRangeDTO;
import org.springframework.stereotype.Component;

@Component
public class AgeMapper {
  public AgeRangeDTO toDto(AgeRange ageRange) {
    if (ageRange == null) {
      return null;
    }
      return new AgeRangeDTO(AgeRangeDTO.RangeEnum.fromValue(ageRange.name()));
  }
}
