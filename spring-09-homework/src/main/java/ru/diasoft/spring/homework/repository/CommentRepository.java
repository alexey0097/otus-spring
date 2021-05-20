package ru.diasoft.spring.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.spring.homework.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> { }
