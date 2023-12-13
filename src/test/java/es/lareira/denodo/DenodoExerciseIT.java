package es.lareira.denodo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DenodoExerciseIT {
  @Autowired private MockMvc mockMvc;

  @Test
  void test_get_purchases_with_paginated() throws Exception {
    MockHttpServletRequestBuilder requestBuilder =
        get("/v1/purchases")
            .queryParam("userId", "101")
            .queryParam("page", "1")
            .queryParam("size", "1");
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.page").value(1))
        .andExpect(jsonPath("$.size").value(1))
        .andExpect(jsonPath("$.totalElements").value(2))
        .andExpect(jsonPath("$.totalPages").value(2))
        .andExpect(jsonPath("$.content[0].id").value(206))
        .andExpect(jsonPath("$.content[0].user.id").value(101))
        .andExpect(jsonPath("$.content[0].user.age").value(18))
        .andExpect(jsonPath("$.content[0].date").value("2023-11-20T20:44:00"));
  }

  @Test
  void test_get_purchases_with_amount() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/purchases").queryParam("total", "26");
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.content[0].id").value(204));
  }

  @Test
  void test_get_purchases_with_no_paginated_params() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/purchases").queryParam("userId", "101");
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.totalElements").value(2))
        .andExpect(jsonPath("$.totalPages").value(1));
  }

  @Test
  void test_get_frequency() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/moreFrequentAgeRangePurchases")
            .queryParam("from", "2023-11-21T19:00:00")
            .queryParam("to", "2023-11-21T20:00:00");
    mockMvc
            .perform(requestBuilder)
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.range").value("AGE_RANGE_0_18"));

  }

  @Test
  void test_get_frequency_with_other_parameters() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/moreFrequentAgeRangePurchases")
            .queryParam("from", "2020-11-21T19:00:00")
            .queryParam("to", "2025-11-21T20:00:00");
    mockMvc
            .perform(requestBuilder)
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.range").value("AGE_RANGE_35_44"));
  }
}
