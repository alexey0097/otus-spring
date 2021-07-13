package ru.diasoft.app.service;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.stereotype.Service;
import ru.diasoft.app.client.AuthorClient;
import ru.diasoft.app.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorClient authorClient = Feign.builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .target(AuthorClient.class, "http://localhost:9080");

    @Override
    public List<AuthorDto> findAll() {
        return authorClient.findAll();
    }

    @Override
    public Optional<AuthorDto> findById(Long id) {
        return Optional.ofNullable(authorClient.findById(id));
    }
}
