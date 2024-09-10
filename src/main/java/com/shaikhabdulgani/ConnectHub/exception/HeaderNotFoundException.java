package com.shaikhabdulgani.ConnectHub.exception;

/**
 * Exception thrown to indicate that a required header is not found.
 * This exception is typically used in scenarios where an operation
 * requires the presence of a specific header in the HTTP request.
 */
public class HeaderNotFoundException extends Exception {
    /**
     * Constructs a new {@code HeaderNotFoundException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public HeaderNotFoundException(String message) {
        super(message);
    }
}
