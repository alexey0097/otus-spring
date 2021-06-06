package ru.diasoft.spring.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.service.AuthorService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    void findAllAuthors() throws Exception {

        final var authors = List.of(
                new Author(1L, "Гоголь", "Николай", "Васильевич"),
                new Author(2L, "Толстой", "Лев", "Николаевич"),
                new Author(3L, "Пушкин", "Александр", "Сергеевич"),
                new Author(4L, "Барто", "Агния", "Львовна")
        );

        when(authorService.findAll()).thenReturn(authors);

        final var expectedResponseBody = objectMapper.writeValueAsString(authors);

        final var mvcResult = mockMvc.perform(get("/api/v1/authors")
            .contentType("application/json")
            .content(expectedResponseBody))
            .andExpect(status().isOk())
            .andReturn();

        final var actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

}