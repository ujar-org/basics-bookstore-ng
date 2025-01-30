/*
 * Copyright 2025 IQKV Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.incubator.sample.bookstore.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import com.iqkv.incubator.sample.bookstore.entity.GeoCountry;
import com.iqkv.incubator.sample.bookstore.entity.GeoState;
import com.iqkv.incubator.sample.bookstore.repository.GeoCountryRepository;
import com.iqkv.incubator.sample.bookstore.repository.GeoStateRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = GeoCountryResource.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class GeoCountryResourceTest {

  private final MockMvc mockMvc;

  @MockBean
  private GeoCountryRepository countryRepository;

  @MockBean
  private GeoStateRepository stateRepository;

  public GeoCountryResourceTest(@Autowired MockMvc mockMvc) {
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
