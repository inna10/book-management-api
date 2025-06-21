package com.inna.book_management_api.repositories;

import com.inna.book_management_api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: BookRepository interface for managing Book entities in the database.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
