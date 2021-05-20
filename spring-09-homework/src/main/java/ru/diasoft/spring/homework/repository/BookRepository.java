package ru.diasoft.spring.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.spring.homework.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
