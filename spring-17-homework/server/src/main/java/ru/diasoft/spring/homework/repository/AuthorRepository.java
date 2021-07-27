package ru.diasoft.spring.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.homework.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> { }
