package ru.diasoft.spring.homework.service;

import ru.diasoft.spring.homework.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Long count();
    Genre save(Genre genre);
    Genre update(Genre genre);
    void deleteById(Long id);
    List<Genre> findAll();
    Genre findById(Long id);

}
