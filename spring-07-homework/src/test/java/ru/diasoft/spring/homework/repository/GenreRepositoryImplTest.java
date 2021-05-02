package ru.diasoft.spring.homework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.homework.config.JdbcTestConfiguration;
import ru.diasoft.spring.homework.entity.Genre;
import ru.diasoft.spring.homework.entity.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@JdbcTest
@Sql({"classpath:schema.sql"})
@Import({JdbcTestConfiguration.class, GenreRepositoryImpl.class})
@DisplayName("Репозиторий жанров книг")
@Transactional(propagation = Propagation.SUPPORTS)
class GenreRepositoryImplTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("считает количество жанров")
    void count() {
        Genre genre = new Genre();
        genre.setGenreName("testGenre");
        genreRepository.save(genre);

        Integer count = genreRepository.count();
        assertThat(count).isNotZero();
    }

    @Test
    @DisplayName("ищет список жанров")
    void findAll() {
        Genre genre = new Genre();
        genre.setGenreName("testGenre");
        genreRepository.save(genre);

        List<Genre> list = genreRepository.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет жанр по идентификатору")
    void findById() {
        Genre genre = new Genre();
        genre.setGenreName("testGenre");
        genreRepository.save(genre);

        Optional<Genre> optional = genreRepository.findById(genre.getGenreId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("создает жанр")
    void save() {
        Genre genre = new Genre();
        genre.setGenreName("testGenre");
        genreRepository.save(genre);

        Optional<Genre> optional = genreRepository.findById(genre.getGenreId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("обновляет жанр")
    void update() {
        Genre genre = new Genre();
        genre.setGenreName("testGenre");
        genreRepository.save(genre);

        Optional<Genre> optional = genreRepository.findById(genre.getGenreId());
        assertThat(optional).isPresent();

        optional.ifPresent(genre1 -> {
            genre1.setGenreName("newName");
            genreRepository.update(genre1);

            Optional<Genre> optional2 = genreRepository.findById(genre1.getGenreId());
            assertThat(optional2).isPresent();

            optional2.ifPresent( genre2 -> {
                assertThat(genre2).isEqualTo(genre1);
            });
        });
    }

    @Test
    @DisplayName("удаляет жанр")
    void deleteById() {
        Genre genre = new Genre();
        genre.setGenreName("testGenre");
        genreRepository.save(genre);

        Optional<Genre> optional = genreRepository.findById(genre.getGenreId());
        assertThat(optional).isPresent();

        genreRepository.deleteById(genre.getGenreId());

        Optional<Genre> optional2 = genreRepository.findById(genre.getGenreId());
        assertThat(optional2).isNotPresent();
    }
}