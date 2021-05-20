package ru.diasoft.spring.homework.repository;

import ru.diasoft.spring.homework.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Long count();
    Book save(Book book);
    Book update(Book book);
    void deleteById(Long id);
    List<Book> findAll();
    Optional<Book> findById(Long id);

}
