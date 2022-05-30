package org.ujar.basics.restful.bookstore.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.ujar.basics.restful.bookstore.repository.ProductRepository;

@WebMvcTest(value = ProductController.class)
class ProductControllerTest {

  private final MockMvc mockMvc;

  @MockBean
  private ProductRepository repository;

  ProductControllerTest(@Autowired MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  @SneakyThrows
  void findByIdShouldReceiveNotFound() {
    mockMvc.perform(
            get("/api/v1/products/1"))
        .andExpect(status().isNotFound());

    verify(repository, atLeastOnce()).findById(1L);
  }

  @Test
  @SneakyThrows
  void findAllShouldReceiveEmptyList() {
    mockMvc.perform(
            get("/api/v1/products")
                .param("page", "1")
        )
        .andExpect(status().isOk());

    var captor = ArgumentCaptor.forClass(Pageable.class);
    verify(repository).findAll(captor.capture());
    assertThat(captor.getValue().getPageNumber()).isEqualTo(1L);
  }
}
