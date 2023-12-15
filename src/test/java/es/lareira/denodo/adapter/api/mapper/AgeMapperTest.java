package es.lareira.denodo.adapter.api.mapper;

import es.lareira.denodo.application.domain.model.purchase.AgeRange;
import es.lareira.denodo.generated.model.AgeRangeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgeMapperTest {
  @InjectMocks private AgeMapper ageMapper;

  @Test
  void when_toDto_input_is_null_then_return_null() {
    assertNull(ageMapper.toDto(null));
  }

  @Test
  void when_toDto_return_value() {
    assertEquals(AgeRangeDTO.RangeEnum._0_18, ageMapper.toDto(AgeRange.AGE_RANGE_0_18).getRange());
  }
}
