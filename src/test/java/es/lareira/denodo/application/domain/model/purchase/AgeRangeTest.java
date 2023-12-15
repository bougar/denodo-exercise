package es.lareira.denodo.application.domain.model.purchase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AgeRangeTest {
  @Test
  void test_3_in_range_0_18() {
    assertTrue(AgeRange.AGE_RANGE_0_18.isInRange(3));
  }

  @Test
  void test_18_in_range_0_18() {
    assertTrue(AgeRange.AGE_RANGE_0_18.isInRange(18));
  }

  @Test
  void test_19_in_range_18_24() {
    assertTrue(AgeRange.AGE_RANGE_18_24.isInRange(19));
  }

  @Test
  void test_25_in_range_25_34() {
    assertTrue(AgeRange.AGE_RANGE_25_34.isInRange(25));
  }

  @Test
  void test_34_in_range_25_34() {
    assertTrue(AgeRange.AGE_RANGE_25_34.isInRange(34));
  }

  @Test
  void test_35_in_range_35_44() {
    assertTrue(AgeRange.AGE_RANGE_35_44.isInRange(35));
  }

  @Test
  void test_43_in_range_35_44() {
    assertTrue(AgeRange.AGE_RANGE_35_44.isInRange(43));
  }

  @Test
  void test_45_in_range_45_54() {
    assertTrue(AgeRange.AGE_RANGE_45_54.isInRange(45));
  }

  @Test
  void test_54_in_range_45_54() {
    assertTrue(AgeRange.AGE_RANGE_45_54.isInRange(54));
  }

  @Test
  void test_55_in_range_55_64() {
    assertTrue(AgeRange.AGE_RANGE_55_64.isInRange(55));
  }

  @Test
  void test_64_in_range_55_64() {
    assertTrue(AgeRange.AGE_RANGE_55_64.isInRange(64));
  }

  @Test
  void test_65_in_range_65_plus() {
    assertTrue(AgeRange.AGE_RANGE_65_PLUS.isInRange(65));
  }

  @Test
  void test_100_in_range_65_plus() {
    assertTrue(AgeRange.AGE_RANGE_65_PLUS.isInRange(100));
  }

  @Test
  void from_age_3_get_age_range_0_18() {
    assertEquals(AgeRange.AGE_RANGE_0_18, AgeRange.getAgeRange(3));
  }

  @Test
  void from_age_negative_get_age_range_unknown() {
    assertEquals(AgeRange.AGE_RANGE_UNKNOWN, AgeRange.getAgeRange(-1));
  }
}
