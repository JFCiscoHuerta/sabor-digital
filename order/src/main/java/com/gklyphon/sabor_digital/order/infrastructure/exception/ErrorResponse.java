package com.gklyphon.sabor_digital.order.infrastructure.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents an error response with a message, timestamp, and status code.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
public class ErrorResponse {

    /**
     * The error message.
     */
    private String message;

    /**
     * The timestamp when the error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * The HTTP status code associated with the error.
     */
    private int status;

    /**
     * Default constructor.
     */
    public ErrorResponse() {
    }

    /**
     * Constructs an ErrorResponse with the specified message.
     * The status is not set, and the timestamp is set to the current time.
     *
     * @param message the error message
     */
    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs an ErrorResponse with the specified message and status code.
     * The timestamp is set to the current time.
     *
     * @param message the error message
     * @param status  the HTTP status code
     */
    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs an ErrorResponse with the specified message and HttpStatus.
     * The timestamp is set to the current time.
     *
     * @param message the error message
     * @param status  the HttpStatus object representing the HTTP status code
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
