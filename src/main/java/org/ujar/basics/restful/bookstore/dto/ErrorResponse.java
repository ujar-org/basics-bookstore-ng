package org.ujar.basics.restful.bookstore.dto;

import java.util.List;
import lombok.Value;

@Value
public class ErrorResponse {

  List<Error> errors;

  public static ErrorResponse singleError(Throwable throwable) {
    return singleError(throwable.getMessage());
  }

  public static ErrorResponse singleError(String message) {
    var errors = List.of(new ErrorResponse.Error(message));
    return new ErrorResponse(errors);
  }

  @Value
  public static class Error {
    String message;
  }

}
