package ru.diasoft.spring.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.spring.homework.entity.Genre;
import ru.diasoft.spring.homework.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping(value = "/api/v1/genres", produces = "application/json;charset=UTF-8")
    public List<Genre> findGenres(){
        return genreService.findAll();
    }

    @GetMapping(value = "/api/v1/genre/{id}", produces = "application/json;charset=UTF-8")
    public Genre findGenreById(@PathVariable Long id){
        return genreService.findById(id);
    }

}
