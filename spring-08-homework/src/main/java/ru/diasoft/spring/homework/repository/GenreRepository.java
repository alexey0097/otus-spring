package ru.diasoft.spring.homework.repository;

import ru.diasoft.spring.homework.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Long count();
    Genre save(Genre genre);
    Genre update(Genre genre);
    void deleteById(Long id);
    List<Genre> findAll();
    Optional<Genre> findById(Long id);

}
