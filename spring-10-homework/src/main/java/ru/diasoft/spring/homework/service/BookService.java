package ru.diasoft.spring.homework.service;

import ru.diasoft.spring.homework.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Long count();
    Book save(Book book);
    Book update(Book book);
    void deleteById(Long id);
    List<Book> findAll();
    Book findById(Long id);
}
