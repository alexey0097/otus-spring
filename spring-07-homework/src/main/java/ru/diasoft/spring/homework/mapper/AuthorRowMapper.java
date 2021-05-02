package ru.diasoft.spring.homework.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("middle_name")
        );
    }
}
