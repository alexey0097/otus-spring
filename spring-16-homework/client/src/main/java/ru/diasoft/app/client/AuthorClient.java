package ru.diasoft.app.client;

import feign.Param;
import feign.RequestLine;
import ru.diasoft.app.dto.AuthorDto;

import java.util.List;

public interface AuthorClient {

    @RequestLine("GET /api/v1/authors")
    List<AuthorDto> findAll();

    @RequestLine("GET /api/v1/author/{id}")
    AuthorDto findById(@Param("id") Long id);

}
