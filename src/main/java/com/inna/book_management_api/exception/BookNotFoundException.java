package com.inna.book_management_api.exception;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: BookNotFoundException is thrown when a book is not found in the system.
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
