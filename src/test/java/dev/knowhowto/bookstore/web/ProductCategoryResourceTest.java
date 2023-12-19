package dev.knowhowto.bookstore.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.knowhowto.bookstore.repository.ProductCategoryRepository;
import dev.knowhowto.bookstore.repository.ProductRepository;
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
