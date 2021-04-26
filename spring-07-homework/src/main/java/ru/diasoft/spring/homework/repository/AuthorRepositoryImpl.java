package ru.diasoft.spring.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.mapper.AuthorRowMapper;

import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject(
                "select count(*) from authors",
                new MapSqlParameterSource(),
                Integer.class
        );
    }

    @Override
    public Author save(Author author) {
        Long id = (long) jdbcTemplate.update(
                "insert into authors(last_name, first_name, middle_name) values(:lastName, :firstName, :middleName)",
                new BeanPropertySqlParameterSource(author)
        );
        author.setAuthorId(id);
        return author;
    }

    @Override
    public Author update(Author author) {
        jdbcTemplate.update(
                "update authors set last_name = :lastName, first_name = :firstName, middle_name = :middleName where author_id = :authorId",
                new BeanPropertySqlParameterSource(author)
        );
        return author;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(
                "delete authors where author_id = :authorId",
                new MapSqlParameterSource("authorId", id));
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query(
                "select * from authors",
                new AuthorRowMapper()
        );
    }

    @Override
    public Optional<Author> findById(Long authorId) {
        List<Author> result = jdbcTemplate.query(
                "select * from authors where author_id = :authorId",
                new MapSqlParameterSource("authorId", authorId),
                new AuthorRowMapper()
        );
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(result.get(0));
        }
    }
}
