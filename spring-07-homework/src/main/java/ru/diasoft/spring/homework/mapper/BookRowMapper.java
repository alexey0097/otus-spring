package ru.diasoft.spring.homework.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getLong("book_id"));
        book.setBookName(resultSet.getString("book_name"));

        Genre genre = new Genre();
        genre.setGenreId(resultSet.getLong("genre_id"));
        genre.setGenreName(resultSet.getString("genre_name"));
        book.setGenre(genre);

        Author author = new Author();
        author.setAuthorId(resultSet.getLong("author_id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        book.setAuthor(author);

        return book;
    }
}
