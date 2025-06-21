package com.inna.book_management_api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: GlobalExceptionHandler is a centralized exception handler for the Book Management API.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles BookNotFoundException when a book is not found in the system.
     *
     * @param ex BookNotFoundException instance containing the error details.
     * @return ResponseEntity with ApiError containing the error details and HTTP status 404 (Not Found).
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiError> handleBookNotFoundException(BookNotFoundException ex) {
        logger.warn("Book not found exception: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                "Book not found",
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles DuplicateBookException when a book with the same details already exists.
     *
     * @param ex DuplicateBookException instance containing the error details.
     * @return ResponseEntity with ApiError containing the error details and HTTP status 409 (Conflict).
     */
    @ExceptionHandler(DuplicateBookException.class)
    public ResponseEntity<ApiError> handleDuplicateBookException(DuplicateBookException ex) {
        logger.warn("Duplicate book exception: {}", ex.getMessage());
        // return a 409 Conflict, since this situation indicates a conflict with the current state of the resource
        ApiError apiError = new ApiError(
                HttpStatus.CONFLICT,
                "Duplicate book error",
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    /**
     * Handles IllegalArgumentException when an invalid argument is provided.
     *
     * @param ex IllegalArgumentException instance containing the error details.
     * @return ResponseEntity with ApiError containing the error details and HTTP status 400 (Bad Request).
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Illegal argument exception: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid input",
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentNotValidException when validation fails for a request body.
     *
     * @param ex MethodArgumentNotValidException instance containing the validation errors.
     * @return ResponseEntity with ApiError containing the validation errors and HTTP status 400 (Bad Request).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        logger.error("Validation error occurred: {}", ex.getMessage());
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String errorMessage = error.getField() + ": " + error.getDefaultMessage();
            logger.debug("Validation error detail: {}", errorMessage);
            errors.add(errorMessage);
        }

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                errors
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all uncaught exceptions in the application.
     *
     * @param ex Exception instance containing the error details.
     * @return ResponseEntity with ApiError containing the error details and HTTP status 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtExceptions(Exception ex) {
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                List.of(ex.getMessage())
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
