package com.gklyphon.sabor_digital.order.infrastructure.exception.exceptions;

/**
 * Exception thrown when an element is not found.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
public class ElementNotFoundException extends RuntimeException {

    /**
     * Constructs a new ElementNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ElementNotFoundException(String message) {
        super(message);
    }
}
