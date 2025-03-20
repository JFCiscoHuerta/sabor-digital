package com.gklyphon.sabor_digital.waiter.infrastructure.exception;

import com.gklyphon.sabor_digital.waiter.infrastructure.exception.exceptions.ElementNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling application-wide exceptions.
 * This class centralizes exception management using Spring's {@code @RestControllerAdvice}.
 * It provides custom error responses for various types of exceptions.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions when an entity is not found.
     *
     * @param ex The thrown {@link ElementNotFoundException}.
     * @return A {@link ResponseEntity} containing an {@link ErrorResponse} with a {@code NOT_FOUND} status.
     */
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleElementNotFoundException(ElementNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions related to service layer failures.
     *
     * @param ex The thrown {@link ServiceException}.
     * @return A {@link ResponseEntity} containing an {@link ErrorResponse} with an {@code INTERNAL_SERVER_ERROR} status.
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles validation errors for method arguments.
     *
     * @param ex The thrown {@link MethodArgumentNotValidException}.
     * @return A {@link ResponseEntity} containing a map of field errors with a {@code BAD_REQUEST} status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( (err) -> {
            String field = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            errors.put(field, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles generic exceptions that are not explicitly handled by other methods.
     *
     * @param ex The thrown {@link Exception}.
     * @return A {@link ResponseEntity} containing an {@link ErrorResponse} with an {@code INTERNAL_SERVER_ERROR} status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Builds a structured error response.
     *
     * @param ex The thrown exception.
     * @param httpStatus The HTTP status to be returned.
     * @return A {@link ResponseEntity} containing an {@link ErrorResponse}.
     */
    public ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(new ErrorResponse(ex.getMessage(), httpStatus));
    }

}
