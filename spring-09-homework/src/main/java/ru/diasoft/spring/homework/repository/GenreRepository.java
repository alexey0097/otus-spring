package ru.diasoft.spring.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.spring.homework.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> { }
