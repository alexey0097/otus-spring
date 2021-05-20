package ru.diasoft.spring.homework.service;

import ru.diasoft.spring.homework.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Long count();
    Author save(Author author);
    Author update(Author author);
    void deleteById(Long id);
    List<Author> findAll();
    Optional<Author> findById(Long id);
}
