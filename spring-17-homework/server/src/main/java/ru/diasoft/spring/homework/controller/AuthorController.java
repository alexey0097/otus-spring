package ru.diasoft.spring.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(value = "/api/v1/authors", produces = "application/json;charset=UTF-8")
    public List<Author> findAuthors(){
        return authorService.findAll();
    }

    @GetMapping(value = "/api/v1/author/{id}", produces = "application/json;charset=UTF-8")
    public Author findAuthorById(@PathVariable Long id){
        return authorService.findById(id);
    }

}
