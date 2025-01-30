/*
 * Copyright 2025 IQKV Team.
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

import java.util.List;

import com.iqkv.boot.mvc.rest.ApiError;
import com.iqkv.incubator.sample.bookstore.entity.GeoCountry;
import com.iqkv.incubator.sample.bookstore.entity.GeoState;
import com.iqkv.incubator.sample.bookstore.exception.EntityNotFoundException;
import com.iqkv.incubator.sample.bookstore.repository.GeoCountryRepository;
import com.iqkv.incubator.sample.bookstore.repository.GeoStateRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Geo Country", description = "API for geo countries management.")
@RequestMapping("/api/v1/countries")
@Validated
@RequiredArgsConstructor
class GeoCountryResource {

  private final GeoCountryRepository countryRepository;

  private final GeoStateRepository stateRepository;

  @GetMapping("/{id}")
  @Operation(
      description = "Retrieve geo country by id.",
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
  ResponseEntity<GeoCountry> findById(@PathVariable final Long id) {
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
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
      })
  ResponseEntity<List<GeoCountry>> findAll() {
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
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
      })
  ResponseEntity<List<GeoState>> findStatesByCountryId(@PathVariable final Long id) {
    final var country = existingCountry(id);
    return new ResponseEntity<>(stateRepository.findAllByCountry(country), HttpStatus.OK);
  }

  private GeoCountry existingCountry(Long id) throws EntityNotFoundException {
    return countryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Country with id = " + id + " could not found."));
  }
}
