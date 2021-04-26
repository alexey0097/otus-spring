package ru.diasoft.spring.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.mapper.BookRowMapper;

import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject(
                "select count(*) from books",
                new MapSqlParameterSource(),
                Integer.class
        );
    }

    @Override
    public Book save(Book book) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into books");
        stringBuilder.append("( ");
        stringBuilder.append("book_name");
        if (book.getAuthor() != null && book.getAuthor().getAuthorId() >= 1L) {
            stringBuilder.append(", author_id");
        }
        if (book.getGenre() != null && book.getGenre().getGenreId() >= 1L) {
            stringBuilder.append(", genre_id");
        }
        stringBuilder.append(")  values (");
        stringBuilder.append(":bookName");
        if (book.getAuthor() != null && book.getAuthor().getAuthorId() >= 1L) {
            stringBuilder.append(", :author.authorId");
        }
        if (book.getGenre() != null && book.getGenre().getGenreId() >= 1L) {
            stringBuilder.append(", :genre.genreId");
        }
        stringBuilder.append(")");
        Long id = (long) jdbcTemplate.update(stringBuilder.toString(), getMapSqlParameterSource(book));
        book.setBookId(id);
        return book;
    }

    @Override
    public Book update(Book book) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("update books set ");
        stringBuilder.append("book_name = :bookName ");
        if (book.getAuthor() != null && book.getAuthor().getAuthorId() >= 1L) {
            stringBuilder.append(",author_id = :author.authorId ");
        }
        if (book.getGenre() != null && book.getGenre().getGenreId() >= 1L) {
            stringBuilder.append(",genre_id = :genre.genreId ");
        }
        stringBuilder.append("where book_id = :bookId");
        jdbcTemplate.update(stringBuilder.toString(), getMapSqlParameterSource(book));
        return book;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(
                "delete books where book_id = :bookId",
                new MapSqlParameterSource("bookId", id));
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(
                "select * from books " +
                        "left join genres on books.genre_id = genres.genre_id " +
                        "left join authors on books.author_id = authors.author_id",
                new BookRowMapper()
        );
    }

    @Override
    public Optional<Book> findById(Long id) {
        List<Book> result = jdbcTemplate.query(
                "select * from books  " +
                        "left join genres on books.genre_id = genres.genre_id " +
                        "left join authors on books.author_id = authors.author_id " +
                        "where book_id = :bookId",
                new MapSqlParameterSource("bookId", id),
                new BookRowMapper()
        );
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(result.get(0));
        }
    }

    private MapSqlParameterSource getMapSqlParameterSource(Book book) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("bookId", book.getBookId());
        mapSqlParameterSource.addValue("bookName", book.getBookName());
        if (book.getAuthor() != null && book.getAuthor().getAuthorId() >= 1L) {
            mapSqlParameterSource.addValue("author.authorId", book.getAuthor().getAuthorId());
        }
        if (book.getGenre() != null && book.getGenre().getGenreId() >= 1L) {
            mapSqlParameterSource.addValue("genre.genreId", book.getGenre().getGenreId());
        }
        return mapSqlParameterSource;
    }
}
