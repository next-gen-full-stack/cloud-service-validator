package com.validator.handlers;

import com.validator.beans.base.ServiceValidationError;
import com.validator.exceptions.ServiceValidationException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ServiceValidationExceptionHandler {

  @ExceptionHandler(ServiceValidationException.class)
  @ResponseBody
  ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {

    HttpStatus status = getStatus(request);
    return new ResponseEntity<>(
        new ServiceValidationError(status.value(), ex.getMessage()), status);
  }

  private HttpStatus getStatus(HttpServletRequest request) {

    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }
}
