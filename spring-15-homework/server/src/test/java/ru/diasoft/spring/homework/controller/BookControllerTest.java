package ru.diasoft.spring.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.entity.Genre;
import ru.diasoft.spring.homework.service.BookService;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
@DisplayName("Контроллер для книг")
class BookControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private BookService bookService;

    @DisplayName("возвращает список книг")
    @Test
    void findAllBooks() throws Exception {

        final var author = new Author(1L, "Гоголь", "Николай", "Васильевич");
        final var genre = new Genre(1L, "Фантастика");

        final var books = List.of(
                new Book(1L, "имяКниги", author, genre, new LinkedList<>()),
                new Book(2L, "имяКниги", author, genre, new LinkedList<>())
        );

        when(bookService.findAll()).thenReturn(books);

        final String expectedResponseBody = objectMapper.writeValueAsString(books);

        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/books")
                .contentType("application/json")
                .content(expectedResponseBody))
                .andExpect(status().isOk())
                .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @DisplayName("возвращает книгу по ее идентификатору")
    @Test
    void findBookById() throws Exception {
        final var author = new Author(1L, "Гоголь", "Николай", "Васильевич");
        final var genre = new Genre(1L, "Фантастика");

        final var book = new Book(1L, "имяКниги", author, genre, new LinkedList<>());

        when(bookService.findById(anyLong())).thenReturn(book);

        final String expectedResponseBody = objectMapper.writeValueAsString(book);

        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/book/1")
                .contentType("application/json")
                .content(expectedResponseBody))
                .andExpect(status().isOk())
                .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @DisplayName("создает книгу")
    @Test
    void createBook() throws Exception {
        final var author = new Author(1L, "Гоголь", "Николай", "Васильевич");
        final var genre = new Genre(1L, "Фантастика");

        final var book = new Book(1L, "имяКниги", author, genre, new LinkedList<>());

        when(bookService.save(any())).thenReturn(book);

        final String expectedResponseBody = objectMapper.writeValueAsString(book);

        final MvcResult mvcResult = mockMvc.perform(post("/api/v1/book")
                        .contentType("application/json")
                        .content(expectedResponseBody))
                .andExpect(status().isOk())
                .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @DisplayName("обновляет книгу")
    @Test
    void updateBook() throws Exception {
        final var author = new Author(1L, "Гоголь", "Николай", "Васильевич");
        final var genre = new Genre(1L, "Фантастика");

        final var book = new Book(1L, "имяКниги", author, genre, new LinkedList<>());

        when(bookService.update(any())).thenReturn(book);

        final String expectedResponseBody = objectMapper.writeValueAsString(book);

        final MvcResult mvcResult = mockMvc.perform(put("/api/v1/book/1")
                        .contentType("application/json")
                        .content(expectedResponseBody))
                .andExpect(status().isOk())
                .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @DisplayName("удаляет книгу")
    @Test
    void deleteBook() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(delete("/api/v1/book/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        final Integer status = mvcResult.getResponse().getStatus();

        assertThat(status).isEqualTo(200);
    }

}
