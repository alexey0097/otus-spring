package ru.diasoft.spring.homework.repository;

import ru.diasoft.spring.homework.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Long count();
    Author save(Author author);
    Author update(Author book);
    void deleteById(Long id);
    List<Author> findAll();
    Optional<Author> findById(Long id);

}
