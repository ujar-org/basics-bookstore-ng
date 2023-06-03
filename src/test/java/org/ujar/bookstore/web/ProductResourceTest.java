package org.ujar.bookstore.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.ujar.bookstore.entity.Product;
import org.ujar.bookstore.repository.ProductRepository;

@WebMvcTest(value = ProductResource.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class ProductResourceTest {

  private final MockMvc mockMvc;

  @MockBean
  private ProductRepository productRepository;

  ProductResourceTest(@Autowired MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  @SneakyThrows
  void findByIdShouldReturnNotFound() {
    mockMvc.perform(
            get("/api/v1/products/1"))
        .andExpect(status().isNotFound());

    verify(productRepository, atLeastOnce()).findById(1L);
  }

  @Test
  @SneakyThrows
  void findAllByNameShouldReturnOneLinuxBook() {
    final var pageRequest = PageRequest.of(0, 10);
    when(productRepository.findByNameContaining("Linux", pageRequest))
        .thenReturn(new PageImpl<>(List.of(new Product())));

    mockMvc.perform(
            get("/api/v1/products").param("name", "Linux"))
        .andDo(print())
        .andExpect(status().isOk());

    verify(productRepository).findByNameContaining("Linux", pageRequest);
  }

  @Test
  @SneakyThrows
  void findAllShouldReturnEmptyList() {
    mockMvc.perform(
            get("/api/v1/products")
                .param("page", "1")
        )
        .andExpect(status().isOk());

    final var captor = ArgumentCaptor.forClass(Pageable.class);

    verify(productRepository).findAll(captor.capture());
    assertThat(captor.getValue().getPageNumber()).isEqualTo(1L);
  }
}
