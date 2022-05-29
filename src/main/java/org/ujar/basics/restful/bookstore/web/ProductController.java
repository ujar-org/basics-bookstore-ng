package org.ujar.basics.restful.bookstore.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.basics.restful.bookstore.entity.Product;
import org.ujar.basics.restful.bookstore.repository.ProductRepository;
import org.ujar.basics.restful.bookstore.web.dto.ErrorResponse;
import org.ujar.boot.starter.restful.web.dto.PageRequestDto;

@RestController
@Tag(name = "Product controller", description = "API for products management.")
@RequestMapping("/api/v1/products")
@Validated
@RequiredArgsConstructor
public class ProductController {

  private final ProductRepository repository;

  @GetMapping("/{id}")
  @Operation(
      description = "Retrieve product by id.",
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
  public ResponseEntity<Product> findById(@PathVariable Long id) {
    return ResponseEntity.of(repository.findById(id));
  }

  @GetMapping
  @Operation(
      description = "Retrieve products list.",
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
  public ResponseEntity<Page<Product>> findAll(@ParameterObject @Valid PageRequestDto request) {
    var pageRequest = PageRequest.of(request.getOffset(), request.getLimit());
    return new ResponseEntity<>(repository.findAll(pageRequest), HttpStatus.OK);
  }
}
