package com.inna.book_management_api.controllers;

import com.inna.book_management_api.config.SecurityConfig;
import com.inna.book_management_api.models.Book;
import com.inna.book_management_api.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: Test class for BookController to verify the functionality of the book management API.
 */
@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("getAllBooks should return a list of books")
    @WithMockUser(username = "user", roles = "USER")//since the controller is secured, we need to mock a user with the USER role
    public void getAllBooks_ShouldReturnListOfBooks() throws Exception {
        // Arrange
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        Book book2 = new Book("1984", "George Orwell", 1949);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books); // Mock the service to return a list of books

        // Act & Assert
        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].title").value("The Hobbit"))
                .andExpect(jsonPath("$.[0].author").value("J.R.R. Tolkien"))
                .andExpect(jsonPath("$.[0].publishedYear").value(1937))
                .andExpect(jsonPath("$.[1].title").value("1984"))
                .andExpect(jsonPath("$.[1].author").value("George Orwell"))
                .andExpect(jsonPath("$.[1].publishedYear").value(1949));
    }

    @Test
    @DisplayName("getAllBooks should return an empty list when no books are present")
    @WithMockUser(username = "user", roles = "USER")
    public void getAllBooks_WhenNoBooks_ShouldReturnEmptyList() throws Exception {
        // Arrange
        when(bookService.getAllBooks()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("getAllBooks should return 401 Unauthorized when user is not authenticated")
    public void getAllBooks_WhenUnauthorized_ShouldReturn401() throws Exception {
        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
