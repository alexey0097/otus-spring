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
import ru.diasoft.spring.homework.entity.Genre;
import ru.diasoft.spring.homework.service.GenreService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GenreController.class)
@DisplayName("Контроллер для жанров")
class GenreControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean
    private GenreService genreService;

    @DisplayName("возвращает список жанров")
    @Test
    void findAllGenres() throws Exception {

        final var genres = List.of(
                new Genre(1L, "Фантастика"),
                new Genre(2L, "Приключения"),
                new Genre(3L, "Мистика"),
                new Genre(4L, "Детективы")
        );

        when(genreService.findAll()).thenReturn(genres);

        final String expectedResponseBody = objectMapper.writeValueAsString(genres);

        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/genres")
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
        final var genre = new Genre(1L, "Фантастика");

        when(genreService.findById(any())).thenReturn(genre);

        final String expectedResponseBody = objectMapper.writeValueAsString(genre);

        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/genre/1")
                        .contentType("application/json")
                        .content(expectedResponseBody))
                .andExpect(status().isOk())
                .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }
    
}
