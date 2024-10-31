package com.datta.cardiac_arrest.exception;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {
  private String errorCode;

  public UserAlreadyExistsException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}