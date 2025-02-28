package com.gklyphon.sabor_digital.waiter.infrastructure.exception;

import com.gklyphon.sabor_digital.waiter.infrastructure.exception.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(com.gklyphon.sabor_digital.waiter.infrastructure.exception.exceptions.ElementNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleElementNotFoundException(ElementNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(new ErrorResponse(ex.getMessage(), httpStatus));
    }

}
