package com.inna.book_management_api.exception;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: DuplicateBookException is thrown when a book with the same title already exists in the system.
 */
public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String message) {
        super(message);
    }
}
