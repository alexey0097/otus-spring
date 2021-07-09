package ru.diasoft.app.service;

import feign.Param;
import ru.diasoft.app.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<CountryDto> findAll();
    Optional<CountryDto> findByName(String name);
}
