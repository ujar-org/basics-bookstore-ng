package org.ujar.bookstore.web;

import java.util.List;

public record ErrorResponse(List<Error> errors) {

  public static ErrorResponse singleError(Throwable throwable) {
    return singleError(throwable.getMessage());
  }

  public static ErrorResponse singleError(String message) {
    final var errors = List.of(new ErrorResponse.Error(message));
    return new ErrorResponse(errors);
  }

  public record Error(String message) {

  }
}
