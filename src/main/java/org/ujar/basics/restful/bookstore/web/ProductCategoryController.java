package org.ujar.basics.restful.bookstore.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.basics.restful.bookstore.entity.Product;
import org.ujar.basics.restful.bookstore.entity.ProductCategory;
import org.ujar.basics.restful.bookstore.exception.EntityNotFoundException;
import org.ujar.basics.restful.bookstore.repository.ProductCategoryRepository;
import org.ujar.basics.restful.bookstore.repository.ProductRepository;
import org.ujar.basics.restful.bookstore.web.dto.ErrorResponse;
import org.ujar.boot.starter.restful.web.dto.PageRequestDto;

@RestController
@Tag(name = "Product category controller", description = "API for product categories management.")
@RequestMapping("/api/v1/product-categories")
@Validated
@RequiredArgsConstructor
public class ProductCategoryController {

  private final ProductCategoryRepository categoryRepository;

  private final ProductRepository productRepository;

  @GetMapping("/{id}")
  @Operation(
      description = "Retrieve product category by id.",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "404",
                       description = "Entity not found",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      })
  public ResponseEntity<ProductCategory> findById(@PathVariable final Long id) {
    final var category = existingCategory(id);
    return new ResponseEntity<>(category, HttpStatus.OK);
  }

  @GetMapping
  @Operation(
      description = "Retrieve categories list.",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      })
  @Transactional(readOnly = true)
  public ResponseEntity<List<ProductCategory>> findAll() {
    return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}/products")
  @Operation(
      description = "Retrieve products in specified category.",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      })
  @Transactional(readOnly = true)
  public ResponseEntity<Page<Product>> findProductsByCategoryId(@PathVariable final Long id,
                                                                @ParameterObject @Valid PageRequestDto request) {
    final var category = existingCategory(id);
    final var pageRequest = PageRequest.of(request.getPage(), request.getLimit());
    return new ResponseEntity<>(productRepository.findAllByCategory(category, pageRequest), HttpStatus.OK);
  }

  private ProductCategory existingCategory(Long id) throws EntityNotFoundException {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category with id = " + id + " could not found."));
  }
}
