package com.gklyphon.sabor_digital.order.infrastructure.exception;

import com.gklyphon.sabor_digital.order.infrastructure.exception.exceptions.ElementNotFoundException;
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
     * Handles ServiceException and return an error response.
     *
     * @param ex the ServiceException instance
     * @return a ResponseEntity containing the ErrorResponse and HTTP status
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles Exception and return an error response.
     *
     * @param ex the Exception instance
     * @return a ResponseEntity containing the ErrorResponse and HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles validation errors when a request body fails @Valid checks.
     *
     * This method catches exceptions of type {@link MethodArgumentNotValidException},
     * which are thrown when a request body annotated with {@link jakarta.validation.Valid} fails validation.
     * It extracts validation error messages and returns them in a structured format.
     *
     * @param ex the {@link MethodArgumentNotValidException} instance containing validation errors.
     * @return a {@link ResponseEntity} with a map of field names and their corresponding error messages,
     * along with a {@link HttpStatus#BAD_REQUEST} (400) response status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleArgumentValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( (err) -> {
                String field = ((FieldError) err).getField();
                String errorMessage = err.getDefaultMessage();
                errors.put(field, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
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
