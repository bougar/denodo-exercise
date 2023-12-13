package es.lareira.denodo.application.domain.model.purchase;


import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public enum AgeRange {
  AGE_RANGE_UNKNOWN(IntStream::empty),
  AGE_RANGE_0_18(() -> IntStream.rangeClosed(0, 18)),
  AGE_RANGE_18_24(() -> IntStream.rangeClosed(18, 24)),
  AGE_RANGE_25_34(() -> IntStream.rangeClosed(25, 34)),
  AGE_RANGE_35_44(() -> IntStream.rangeClosed(35, 44)),
  AGE_RANGE_45_54(() -> IntStream.rangeClosed(45, 54)),
  AGE_RANGE_55_64(() -> IntStream.rangeClosed(55, 64)),
  AGE_RANGE_65_PLUS(() -> IntStream.range(65, Integer.MAX_VALUE));

  private final Supplier<IntStream> rangeFactory;

  AgeRange(Supplier<IntStream> rangeFactory) {
    this.rangeFactory = rangeFactory;
  }

  public IntStream getRange() {
    return rangeFactory.get();
  }

  public boolean isInRange(int age) {
    return getRange().anyMatch(i -> i == age);
  }

  public static AgeRange getAgeRange(int age) {
    return Arrays.stream(values())
            .filter(ageRange -> ageRange.isInRange(age))
            .findFirst()
            .orElse(AGE_RANGE_UNKNOWN);
  }
}
