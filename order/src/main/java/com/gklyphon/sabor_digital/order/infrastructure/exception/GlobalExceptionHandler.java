package com.gklyphon.sabor_digital.order.infrastructure.exception;

import com.gklyphon.sabor_digital.order.infrastructure.exception.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for REST controllers.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ElementNotFoundException and returns a corresponding error response.
     *
     * @param ex the ElementNotFoundException instance
     * @return a ResponseEntity containing the ErrorResponse and HTTP status
     */
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleElementNotFoundException(ElementNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    /**
     * Builds an error response with the given exception and HTTP status.
     *
     * @param ex the exception that occurred
     * @param httpStatus the HTTP status to be returned
     * @return a ResponseEntity containing the ErrorResponse and HTTP status
     */
    public ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(new ErrorResponse(ex.getMessage(), httpStatus));
    }

}
