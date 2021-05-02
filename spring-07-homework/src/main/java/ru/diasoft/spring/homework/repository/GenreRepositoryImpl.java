package ru.diasoft.spring.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.homework.entity.Genre;
import ru.diasoft.spring.homework.mapper.GenreRowMapper;

import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject(
                "select count(*) from genres",
                new MapSqlParameterSource(),
                Integer.class
        );
    }

    @Override
    public Genre save(Genre genres) {
        Long id = (long) jdbcTemplate.update(
                "insert into genres(genre_name) values(:genreName)",
                new BeanPropertySqlParameterSource(genres)
        );
        genres.setGenreId(id);
        return genres;
    }

    @Override
    public Genre update(Genre genres) {
        jdbcTemplate.update(
                "update genres set genre_name = :genreName where genre_id = :genreId",
                new BeanPropertySqlParameterSource(genres)
        );
        return genres;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(
                "delete genres where genre_id = :genreId",
                new MapSqlParameterSource("genreId", id));
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(
                "select * from genres",
                new GenreRowMapper()
        );
    }

    @Override
    public Optional<Genre> findById(Long id) {
        List<Genre> result = jdbcTemplate.query(
                "select * from genres where genre_id = :genreId",
                new MapSqlParameterSource("genreId", id),
                new GenreRowMapper()
        );
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(result.get(0));
        }
    }
}
