package org.ujar.bs.rst.bookstore.web;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.boot.starter.restful.web.dto.PageRequestDto;
import org.ujar.bs.rst.bookstore.entity.GeoState;
import org.ujar.bs.rst.bookstore.repository.GeoStateRepository;
import org.ujar.bs.rst.bookstore.web.dto.ErrorResponse;

@RestController
@Tag(name = "Geo State controller", description = "API for geo states management.")
@RequestMapping("/api/v1/states")
@Validated
@RequiredArgsConstructor
class GeoStateController {
  private final GeoStateRepository geoStateRepository;

  @GetMapping("/{id}")
  @Operation(
      description = "Retrieve state by id.",
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
  ResponseEntity<GeoState> findById(@PathVariable final Long id) {
    return ResponseEntity.of(geoStateRepository.findById(id));
  }

  @GetMapping
  @Operation(
      description = "Retrieve states list.",
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
  ResponseEntity<Page<GeoState>> findAll(@ParameterObject @Valid final PageRequestDto request) {
    final var pageRequest = PageRequest.of(request.getPage(), request.getSize());
    return new ResponseEntity<>(geoStateRepository.findAll(pageRequest), HttpStatus.OK);
  }
}
