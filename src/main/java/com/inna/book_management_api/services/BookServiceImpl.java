package com.inna.book_management_api.services;

import com.inna.book_management_api.exception.BookNotFoundException;
import com.inna.book_management_api.exception.DuplicateBookException;
import com.inna.book_management_api.models.Book;
import com.inna.book_management_api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: BookService interface for managing book operations in the system.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Retrieves all books from the database.
     *
     * @return
     */
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found in the database");
        }
        return books;
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id The ID of the book to retrieve.
     * @return The book with the specified ID.
     * @throws BookNotFoundException if no book is found with the given ID.
     */
    @Override
    public Book getBookById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    /**
     * Creates a new book in the database.
     *
     * @param book
     * @return
     */
    @Override
    public Book createBook(@Valid Book book) {
        validateBook(book);
        checkDuplicateBook(book);
        return bookRepository.save(book);
    }

    /**
     * Updates an existing book in the database.
     *
     * @param id          The ID of the book to update.
     * @param bookDetails The details to update the book with.
     * @return The updated book.
     * @throws BookNotFoundException if no book is found with the given ID.
     */
    @Override
    public Book updateBook(Long id, @Valid Book bookDetails) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }

        Book existingBook = getBookById(id);
        validateBook(bookDetails);

        // Check if update would create a duplicate, excluding the current book
        if (isDuplicateBookExcludingId(bookDetails, id)) {
            throw new DuplicateBookException("A book with the same title and author already exists");
        }

        // Update only non-null fields
        if (bookDetails.getTitle() != null) {
            existingBook.setTitle(bookDetails.getTitle());
        }
        if (bookDetails.getAuthor() != null) {
            existingBook.setAuthor(bookDetails.getAuthor());
        }
        if (bookDetails.getPublishedYear() != null) {
            existingBook.setPublishedYear(bookDetails.getPublishedYear());
        }

        return bookRepository.save(existingBook);
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id The ID of the book to delete.
     * @throws IllegalArgumentException if the ID is null.
     * @throws BookNotFoundException    if no book is found with the given ID.
     */
    @Override
    public void deleteBook(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    /**
     * Validates the book object to ensure it meets the required criteria.
     *
     * @param book
     */
    private void validateBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be null or empty");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Book author cannot be null or empty");
        }
        if (book.getPublishedYear() == null) {
            throw new IllegalArgumentException("Published year cannot be null");
        }
    }

    /*
     * Checks if a book with the same title and author already exists in the database.
     *
     * @param book The book to check for duplicates.
     * @throws DuplicateBookException if a duplicate book is found.
     */
    private void checkDuplicateBook(Book book) {
        List<Book> existingBooks = bookRepository.findAll();
        boolean isDuplicate = existingBooks.stream()
                .anyMatch(existingBook ->
                        existingBook.getTitle().equalsIgnoreCase(book.getTitle()) &&
                                existingBook.getAuthor().equalsIgnoreCase(book.getAuthor()));

        if (isDuplicate) {
            throw new DuplicateBookException("A book with the same title and author already exists");
        }
    }

    /*
     * Checks if a book with the same title and author already exists in the database,
     * excluding the book with the specified ID.
     *
     * @param book The book to check for duplicates.
     * @param excludeId The ID of the book to exclude from the check.
     * @return true if a duplicate exists, false otherwise.
     */
    private boolean isDuplicateBookExcludingId(Book book, Long excludeId) {
        List<Book> existingBooks = bookRepository.findAll();
        return existingBooks.stream()
                .anyMatch(existingBook ->
                        !Objects.equals(existingBook.getId(), excludeId) &&
                                existingBook.getTitle().equalsIgnoreCase(book.getTitle()) &&
                                existingBook.getAuthor().equalsIgnoreCase(book.getAuthor()));
    }
}
