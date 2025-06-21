package com.inna.book_management_api.controllers;

import com.inna.book_management_api.models.Book;
import com.inna.book_management_api.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: Controller for managing books in the book management API.
 * Provides endpoints to create, read, update, and delete books.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    /**
     * Gets all books from the database.
     *
     * @return List of all books
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        logger.info("Fetching all books");
        List<Book> books = bookService.getAllBooks();
        logger.debug("Found {} books", books.size());
        return ResponseEntity.ok(books);
    }

    /**
     * Gets a book by its ID.
     *
     * @param id The ID of the book to retrieve
     * @return The book with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        logger.info("Fetching book with id: {}", id);
        Book book = bookService.getBookById(id);
        logger.debug("Found book: {}", book);
        return ResponseEntity.ok(book);
    }

    /**
     * Creates a new book in the database.
     *
     * @param book The book to create
     * @return The created book
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        logger.info("Creating new book: {}", book);
        Book createdBook = bookService.createBook(book);
        logger.info("Book created successfully with id: {}", createdBook.getId());
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    /**
     * Updates an existing book in the database.
     *
     * @param id   The ID of the book to update
     * @param book The updated book data
     * @return The updated book
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        logger.info("Updating book with id: {}", id);
        Book updatedBook = bookService.updateBook(id, book);
        logger.info("Book updated successfully");
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id The ID of the book to delete
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        logger.info("Deleting book with id: {}", id);
        bookService.deleteBook(id);
        logger.info("Book deleted successfully");
        return ResponseEntity.noContent().build();
    }
}
