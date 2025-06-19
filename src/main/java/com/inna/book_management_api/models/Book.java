package com.inna.book_management_api.models;

import com.inna.book_management_api.validation.YearNotInFuture;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import static com.inna.book_management_api.models.Constants.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @NotNull(message = "Title cannot be null")
    @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH,
          message = "Title must be between " + MIN_TITLE_LENGTH + " and " + MAX_TITLE_LENGTH + " characters")
    private String title;

    @NotBlank(message = "Author is required")
    @NotNull(message = "Author cannot be null")
    @Size(min = MIN_AUTHOR_LENGTH, max = MAX_AUTHOR_LENGTH,
          message = "Author name must be between " + MIN_AUTHOR_LENGTH + " and " + MAX_AUTHOR_LENGTH + " characters")
    @Pattern(regexp = AUTHOR_NAME_PATTERN, message = "Author name can only contain letters, spaces, dots, and hyphens")
    private String author;

    @NotNull(message = "Published year cannot be null")
    @Min(value = MIN_PUBLISHED_YEAR, message = "Published year must be at least " + MIN_PUBLISHED_YEAR)
    @YearNotInFuture(message = "Published year cannot be in the future")
    private Integer publishedYear;

    // Constructors
    public Book() {
    }

    public Book(String title, String author, Integer publishedYear) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
