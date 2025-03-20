package com.gklyphon.sabor_digital.table.infrastructure.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Exception thrown when an entity or element is not found in the system.
 * This exception extends {@link RuntimeException} and is typically used
 * to indicate that a requested resource does not exist.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private int status;

    /**
     * Default constructor.
     */
    public ErrorResponse() {
    }

    /**
     * Constructs an {@code ErrorResponse} with a message.
     * The timestamp is set to the current time.
     *
     * @param message The error message.
     */
    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs an {@code ErrorResponse} with a message and HTTP status code.
     * The timestamp is set to the current time.
     *
     * @param message The error message.
     * @param status The HTTP status code.
     */
    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs an {@code ErrorResponse} with a message and HTTP status.
     * The timestamp is set to the current time.
     *
     * @param message The error message.
     * @param status The {@link HttpStatus} representing the HTTP status code.
     */
    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
