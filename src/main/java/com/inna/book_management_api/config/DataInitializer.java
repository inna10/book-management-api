package com.inna.book_management_api.config;

import com.inna.book_management_api.models.Book;
import com.inna.book_management_api.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: DataInitializer class to pre-populate the database with sample book data.
 */
@Configuration
public class DataInitializer {
    /**
     * Initializes the database with some sample book data.
     *
     * @param repository the BookRepository to save the books
     * @return a CommandLineRunner that saves sample books to the database
     */
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            repository.save(new Book("1984", "George Orwell", 1949));
            repository.save(new Book("Alice's Adventures in Wonderland", "Lewis Carroll", 1865));
            repository.save(new Book("The Adventures of Tom Sawyer", "Mark Twain", 1876));
            repository.save(new Book("Peter Pan", "J.M. Barrie", 1911));
            repository.save(new Book("Winnie-the-Pooh", "Milne", 1926));
            repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937));
            repository.save(new Book("Don Quixote", "Miguel de Cervantes", 1605));
            repository.save(new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866));
            repository.save(new Book("Anna Karenina", "Leo Tolstoy", 1877));
            repository.save(new Book("Dracula", "Bram Stoker", 1897));
        };
    }
}
