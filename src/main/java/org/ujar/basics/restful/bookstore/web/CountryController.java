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
import org.ujar.basics.restful.bookstore.entity.Country;
import org.ujar.basics.restful.bookstore.entity.State;
import org.ujar.basics.restful.bookstore.exception.EntityNotFoundException;
import org.ujar.basics.restful.bookstore.repository.CountryRepository;
import org.ujar.basics.restful.bookstore.repository.StateRepository;
import org.ujar.basics.restful.bookstore.web.dto.ErrorResponse;

@RestController
@Tag(name = "Geo Country controller", description = "API for geo countries management.")
@RequestMapping("/api/v1/countries")
@Validated
@RequiredArgsConstructor
public class CountryController {

  private final CountryRepository countryRepository;

  private final StateRepository stateRepository;

  @GetMapping("/{id}")
  @Operation(
      description = "Retrieve geo country by id.",
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
  public ResponseEntity<Country> findById(@PathVariable final Long id) {
    final var country = existingCountry(id);
    return new ResponseEntity<>(country, HttpStatus.OK);
  }

  @GetMapping
  @Operation(
      description = "Retrieve countries list.",
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
  public ResponseEntity<List<Country>> findAll() {
    return new ResponseEntity<>(countryRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}/states")
  @Operation(
      description = "Retrieve states in specified country.",
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
  public ResponseEntity<List<State>> findStatesByCountryId(@PathVariable final Long id) {
    final var country = existingCountry(id);
    return new ResponseEntity<>(stateRepository.findAllByCountry(country), HttpStatus.OK);
  }

  private Country existingCountry(Long id) throws EntityNotFoundException {
    return countryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Country with id = " + id + " could not found."));
  }
}
