package ru.diasoft.spring.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.diasoft.spring.homework.config.JwtAuthenticationEntryPoint;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.filter.JwtRequestFilter;
import ru.diasoft.spring.homework.service.AuthorService;
import ru.diasoft.spring.homework.util.JwtTokenUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthorController.class)
@DisplayName("Контроллер для авторов")
class AuthorControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @DisplayName("возвращает список авторов")
    @Test
    void findAllAuthors() throws Exception {

        final var authors = List.of(
                new Author(1L, "Гоголь", "Николай", "Васильевич"),
                new Author(2L, "Толстой", "Лев", "Николаевич"),
                new Author(3L, "Пушкин", "Александр", "Сергеевич"),
                new Author(4L, "Барто", "Агния", "Львовна")
        );

        when(authorService.findAll()).thenReturn(authors);

        final String expectedResponseBody = objectMapper.writeValueAsString(authors);

        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/authors")
            .contentType("application/json")
            .content(expectedResponseBody))
            .andExpect(status().isOk())
            .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @DisplayName("возвращает автора по его идентификатору")
    @Test
    void findAllAuthorById() throws Exception {
        final var author = new Author(1L, "Гоголь", "Николай", "Васильевич");

        when(authorService.findById(any())).thenReturn(author);

        final String expectedResponseBody = objectMapper.writeValueAsString(author);

        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/author/1")
                .contentType("application/json")
                .content(expectedResponseBody))
                .andExpect(status().isOk())
                .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

}