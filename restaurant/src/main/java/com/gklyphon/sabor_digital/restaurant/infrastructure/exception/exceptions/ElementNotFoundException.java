package com.gklyphon.sabor_digital.restaurant.infrastructure.exception.exceptions;

/**
 * Exception thrown when an entity or element is not found in the system.
 * This exception extends {@link RuntimeException} and is typically used
 * to indicate that a requested resource does not exist.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/19
 */
public class ElementNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code ElementNotFoundException} with the specified detail message.
     *
     * @param message The error message describing the missing element.
     */
    public ElementNotFoundException(String message) {
        super(message);
    }
}
