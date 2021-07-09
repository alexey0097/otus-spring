package ru.diasoft.app.service;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.stereotype.Service;
import ru.diasoft.app.client.CountryClient;
import ru.diasoft.app.dto.CountryDto;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryClient countryClient = Feign.builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .target(CountryClient.class, "https://restcountries.eu");

    @Override
    public List<CountryDto> findAll() {
        return countryClient.findAll();
    }

    @Override
    public Optional<CountryDto> findByName(String name) {
        List<CountryDto> countries = countryClient.findByName(name);
        if (!countries.isEmpty()) return Optional.of(countries.get(0));
        return Optional.empty();
    }
}
