package org.ujar.basics.restful.bookstore.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public EntityNotFoundException(String message) {
    super(message);
  }
}
