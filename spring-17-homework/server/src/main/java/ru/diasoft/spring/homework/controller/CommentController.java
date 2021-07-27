package ru.diasoft.spring.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.entity.Comment;
import ru.diasoft.spring.homework.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value = "/api/v1/book/{bookId}/comments", produces = "application/json;charset=UTF-8")
    public List<Comment> findAllByBookId(@PathVariable Long bookId){
        return commentService.findAllByBookId(bookId);
    }

    @PostMapping(value = "/api/v1/book/{bookId}/comment", produces = "application/json;charset=UTF-8")
    public Comment save(@PathVariable Long bookId, Comment comment) {
        return commentService.saveByBookId(bookId, comment);
    }
}
