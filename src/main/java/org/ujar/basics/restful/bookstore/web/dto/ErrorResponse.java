package org.ujar.basics.restful.bookstore.web.dto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public record ErrorResponse(List<Error> errors) {

  public static ErrorResponse singleError(Throwable throwable) {
    return singleError(throwable.getMessage());
  }

  public static ErrorResponse singleError(String message) {
    var errors = List.of(new ErrorResponse.Error(message));
    return new ErrorResponse(errors);
  }

  public record Error(String message) {

  }
}
