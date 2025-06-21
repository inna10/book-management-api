package com.inna.book_management_api.controllers;

import com.inna.book_management_api.models.Book;
import com.inna.book_management_api.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private BookService bookService;

    /**
     * Gets all books from the database.
     *
     * @return List of all books
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * Gets a book by its ID.
     *
     * @param id The ID of the book to retrieve
     * @return The book with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    /**
     * Creates a new book in the database.
     *
     * @param book The book to create
     * @return The created book
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
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
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id The ID of the book to delete
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        // Return 204 No Content status to indicate successful deletion
        return ResponseEntity.noContent().build();
    }
}
