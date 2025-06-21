package com.inna.book_management_api.services;

import com.inna.book_management_api.models.Book;
import java.util.List;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: BookService interface for managing book operations in the system.
 */
public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}
