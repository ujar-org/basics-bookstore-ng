/*
 * Copyright 2024 IQKV.
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.iqkv.incubator.sample.bookstore.repository.ProductCategoryRepository;
import com.iqkv.incubator.sample.bookstore.repository.ProductRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ProductCategoryResource.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class ProductCategoryResourceTest {

  private final MockMvc mockMvc;

  @MockBean
  private ProductCategoryRepository categoryRepository;

  @MockBean
  private ProductRepository productRepository;

  public ProductCategoryResourceTest(@Autowired MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  @SneakyThrows
  void findByIdShouldReturnNotFound() {
    mockMvc.perform(
            get("/api/v1/product-categories/1"))
        .andExpect(status().isNotFound());

    verify(categoryRepository, atLeastOnce()).findById(1L);
  }

  @Test
  @SneakyThrows
  void findProductsByCategoryIdShouldReturnNotFound() {
    mockMvc.perform(
            get("/api/v1/product-categories/1/products"))
        .andExpect(status().isNotFound());

    verify(categoryRepository, atLeastOnce()).findById(1L);
  }

  @Test
  @SneakyThrows
  void findAllShouldReturnEmptyList() {
    mockMvc.perform(
            get("/api/v1/product-categories"))
        .andExpect(status().isOk())
        .andExpect(content().json(
            "[]", true
        ));

    verify(categoryRepository).findAll();
  }
}
