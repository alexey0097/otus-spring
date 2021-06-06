package ru.diasoft.spring.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.spring.homework.entity.Genre;
import ru.diasoft.spring.homework.repository.GenreRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис жанров")
class GenreServiceImplTest {
    
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    @DisplayName("считает жанры")
    void count() {
        when(genreRepository.count()).thenReturn(1L);
        assertThat(genreService.count()).isNotZero();
    }

    @Test
    @DisplayName("сохраняет жанр")
    void save() {
        Genre genre = new Genre();
        genre.setGenreName("GenreName");
        when(genreRepository.save(genre)).thenReturn(genre);

        Genre genre1 = genreService.save(genre);
        assertThat(genre).isEqualTo(genre1);
    }

    @Test
    @DisplayName("обновляет жанр")
    void update() {
        Genre genre = new Genre();
        genre.setGenreName("GenreName");
        when(genreRepository.save(genre)).thenReturn(genre);

        Genre genre1 = genreService.update(genre);
        assertThat(genre).isEqualTo(genre1);
    }

    @Test
    @DisplayName("ищет жанры")
    void findAll() {
        Genre genre = new Genre();
        genre.setGenreName("GenreName");
        when(genreRepository.findAll()).thenReturn(Collections.singletonList(genre));

        List<Genre> list = genreService.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет жанр по ид")
    void findById() {
        Genre genre = new Genre();
        genre.setGenreName("GenreName");
        when(genreRepository.findById(any())).thenReturn(Optional.of(genre));

        Optional<Genre> optionalGenre = genreService.findById(1L);
        assertThat(optionalGenre).isPresent();
    }
}