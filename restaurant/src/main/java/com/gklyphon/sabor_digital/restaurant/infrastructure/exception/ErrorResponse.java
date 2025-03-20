package com.gklyphon.sabor_digital.restaurant.infrastructure.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents a structured error response returned by the API in case of exceptions.
 * This class provides details about the error, including a message, timestamp, and HTTP status code.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
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
