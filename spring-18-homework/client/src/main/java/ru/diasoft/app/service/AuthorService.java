package ru.diasoft.app.service;

import ru.diasoft.app.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDto> findAll();
    Optional<AuthorDto> findById(Long id);
}
