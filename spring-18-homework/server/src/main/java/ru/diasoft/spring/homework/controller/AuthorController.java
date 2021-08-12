package ru.diasoft.spring.homework.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(value = "/api/v1/authors", produces = "application/json;charset=UTF-8")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<Author> findAuthors(){
        List<Author> authors = new ArrayList<>();
        return authors;
    }

    @GetMapping(value = "/api/v1/author/{id}", produces = "application/json;charset=UTF-8")
    public Author findAuthorById(@PathVariable Long id){
        return authorService.findById(id);
    }

}
