/*
 * Copyright 2024 IQKV Team.
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

import jakarta.validation.Valid;

import com.iqkv.boot.mvc.rest.ApiError;
import com.iqkv.boot.mvc.rest.PaginationRequest;
import com.iqkv.incubator.sample.bookstore.entity.Product;
import com.iqkv.incubator.sample.bookstore.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Product", description = "API for products management.")
@RequestMapping("/api/v1/products")
@Validated
@RequiredArgsConstructor
class ProductResource {
  private final ProductRepository productRepository;

  @GetMapping("/{id}")
  @Operation(
      description = "Retrieve product by id.",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
          @ApiResponse(responseCode = "404",
                       description = "Entity not found",
                       content = @Content(schema = @Schema(implementation = ApiError.class)))
      })
  ResponseEntity<Product> findById(@PathVariable final Long id) {
    return ResponseEntity.of(productRepository.findById(id));
  }

  @GetMapping
  @Operation(
      description = "Retrieve products list.",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
      })
  ResponseEntity<Page<Product>> findAll(@RequestParam(required = false) final String name,
                                        @ParameterObject @Valid final PaginationRequest request) {
    final var pageRequest = PageRequest.of(request.getPage(), request.getSize());
    Page<Product> page;
    if (!ObjectUtils.isEmpty(name)) {
      page = productRepository.findByNameContaining(name, pageRequest);
    } else {
      page = productRepository.findAll(pageRequest);
    }
    return new ResponseEntity<>(page, HttpStatus.OK);
  }
}
