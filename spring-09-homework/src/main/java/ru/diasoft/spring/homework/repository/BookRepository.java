package ru.diasoft.spring.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.homework.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
