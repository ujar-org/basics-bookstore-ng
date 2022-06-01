package org.ujar.basics.restful.bookstore.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.ujar.basics.restful.bookstore.entity.Product;
import org.ujar.basics.restful.bookstore.entity.ProductCategory;
import org.ujar.basics.restful.bookstore.repository.ProductCategoryRepository;
import org.ujar.basics.restful.bookstore.repository.ProductRepository;

@WebMvcTest(value = ProductCategoryController.class)
class ProductCategoryControllerTest {

  private final MockMvc mockMvc;

  @MockBean
  private ProductCategoryRepository categoryRepository;

  @MockBean
  private ProductRepository productRepository;

  public ProductCategoryControllerTest(@Autowired MockMvc mockMvc) {
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
  void findProductsByCategoryIdShouldReturnListWithOneBook() {
    var category =  new ProductCategory(1L, "Books", new HashSet<>());
    var pageRequest = PageRequest.of(0, 10);
    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
    when(productRepository.findAllByCategory(category, pageRequest))
        .thenReturn(new PageImpl<>(List.of(new Product())));
    mockMvc.perform(
            get("/api/v1/product-categories/1/products"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(
            """
                {
                  "content":[
                    {
                      "id":null,
                      "category":null,
                      "sku":null,
                      "name":null,
                      "description":null,
                      "unitPrice":null,
                      "imageUrl":null,
                      "active":false,
                      "unitsInStock":0
                    }
                  ],
                  "pageable":"INSTANCE",
                  "totalPages":1,
                  "totalElements":1,
                  "last":true,
                  "size":1,
                  "number":0,
                  "sort":{
                    "empty":true,
                    "sorted":false,
                    "unsorted":true
                  },
                  "numberOfElements":1,
                  "first":true,
                  "empty":false
                }""", true
        ));
    verify(categoryRepository).findById(1L);
    verify(productRepository).findAllByCategory(category, pageRequest);
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
