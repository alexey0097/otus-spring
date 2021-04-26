package ru.diasoft.spring.homework.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.diasoft.spring.homework.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Genre(
                resultSet.getLong("genre_id"),
                resultSet.getString("genre_name")
        );
    }
}
