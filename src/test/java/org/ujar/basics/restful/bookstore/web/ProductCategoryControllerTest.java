package org.ujar.basics.restful.bookstore.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.ujar.basics.restful.bookstore.repository.ProductCategoryRepository;

@WebMvcTest(value = ProductCategoryController.class)
class ProductCategoryControllerTest {

  private final MockMvc mockMvc;

  @MockBean
  private ProductCategoryRepository repository;

  public ProductCategoryControllerTest(@Autowired MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  @SneakyThrows
  void findByIdShouldReceiveNotFound() {
    mockMvc.perform(
            get("/api/v1/product-categories/1"))
        .andDo(print())
        .andExpect(status().isNotFound());
    verify(repository, atLeastOnce()).findById(1L);
  }

  @Test
  @SneakyThrows
  void findAllShouldReceiveEmptyList() {
    mockMvc.perform(
            get("/api/v1/product-categories"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(
            "[]", true
        ));
    verify(repository).findAll();
  }
}
