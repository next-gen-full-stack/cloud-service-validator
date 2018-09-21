package com.validator.beans.base;

public class ServiceValidationError {

  private int code;
  private String message;

  public ServiceValidationError(int code, String message) {

    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
