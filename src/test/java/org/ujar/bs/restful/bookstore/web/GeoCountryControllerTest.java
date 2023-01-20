package org.ujar.bs.rst.bookstore.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.ujar.bs.rst.bookstore.entity.GeoCountry;
import org.ujar.bs.rst.bookstore.entity.GeoState;
import org.ujar.bs.rst.bookstore.repository.GeoCountryRepository;
import org.ujar.bs.rst.bookstore.repository.GeoStateRepository;

@WebMvcTest(value = GeoCountryController.class)
class GeoCountryControllerTest {

  private final MockMvc mockMvc;

  @MockBean
  private GeoCountryRepository countryRepository;

  @MockBean
  private GeoStateRepository stateRepository;

  public GeoCountryControllerTest(@Autowired MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  @SneakyThrows
  void findByIdShouldReturnNotFound() {
    mockMvc.perform(
            get("/api/v1/countries/1"))
        .andExpect(status().isNotFound());

    verify(countryRepository, atLeastOnce()).findById(1L);
  }

  @Test
  @SneakyThrows
  void findStatesByCountryIdShouldReturnNotFound() {
    mockMvc.perform(
            get("/api/v1/countries/1/states"))
        .andExpect(status().isNotFound());

    verify(countryRepository, atLeastOnce()).findById(1L);
  }

  @Test
  @SneakyThrows
  void findStatesByCountryIdShouldReturnListWithOneState() {
    final var country = new GeoCountry(1L, "US", "USA", List.of());
    when(countryRepository.findById(1L)).thenReturn(Optional.of(country));
    when(stateRepository.findAllByCountry(country))
        .thenReturn(List.of(new GeoState()));

    mockMvc.perform(
            get("/api/v1/countries/1/states"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(
            """
                 [{"id":0,"name":null,"country":null}]
                """, true
        ));

    verify(countryRepository).findById(1L);
    verify(stateRepository).findAllByCountry(country);
  }

  @Test
  @SneakyThrows
  void findAllShouldReturnEmptyList() {
    mockMvc.perform(
            get("/api/v1/countries"))
        .andExpect(status().isOk())
        .andExpect(content().json(
            "[]", true
        ));

    verify(countryRepository).findAll();
  }
}
