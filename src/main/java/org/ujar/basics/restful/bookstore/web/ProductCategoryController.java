package org.ujar.basics.restful.bookstore.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.basics.restful.bookstore.entity.ProductCategory;
import org.ujar.basics.restful.bookstore.exception.EntityNotFoundException;
import org.ujar.basics.restful.bookstore.repository.ProductCategoryRepository;
import org.ujar.basics.restful.bookstore.web.dto.ErrorResponse;

@RestController
@Tag(name = "Product category controller", description = "API for product categories management.")
@RequestMapping("/api/v1/product-categories")
@Validated
@RequiredArgsConstructor
public class ProductCategoryController {

  private final ProductCategoryRepository repository;

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
  public ResponseEntity<ProductCategory> findById(@PathVariable Long id) {
    final var category = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category with id = " + id + " could not found."));
    return new ResponseEntity<>(category, HttpStatus.OK);
  }

  @GetMapping
  @Operation(
      description = "Retrieve product categories list.",
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
    return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
  }
}
