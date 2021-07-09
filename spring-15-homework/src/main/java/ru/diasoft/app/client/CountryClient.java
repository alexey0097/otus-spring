package ru.diasoft.app.client;

import feign.Param;
import feign.RequestLine;
import ru.diasoft.app.dto.CountryDto;

import java.util.List;

public interface CountryClient {

    @RequestLine("GET /rest/v2/all")
    List<CountryDto> findAll();

    @RequestLine("GET /rest/v2/name/{name}")
    List<CountryDto> findByName(@Param("name") String name);

}
