package com.datta.cardiac_arrest.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
  private String errorCode;

  public UserNotFoundException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}