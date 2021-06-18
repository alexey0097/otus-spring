package ru.diasoft.spring.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/api/v1/books", produces = "application/json;charset=UTF-8")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping(value = "/api/v1/book/{id}", produces = "application/json;charset=UTF-8")
    public Book findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PostMapping(value = "/api/v1/book", produces = "application/json;charset=UTF-8")
    public Book save(Book book) {
        return bookService.save(book);
    }

    @PutMapping(value = "/api/v1/book/{id}", produces = "application/json;charset=UTF-8")
    public Book update(@PathVariable Long id, Book book){
        return bookService.update(book);
    }

    @DeleteMapping(value = "/api/v1/book/{id}", produces = "application/json;charset=UTF-8")
    public void deleteById(Long id){
        bookService.deleteById(id);
    }

}
