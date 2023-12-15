package es.lareira.denodo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class QueryConfig {
  @Value("${query.minimumFrequencyTotalAmount:100}")
  private Integer minimumFrequencyTotalAmount;
}
