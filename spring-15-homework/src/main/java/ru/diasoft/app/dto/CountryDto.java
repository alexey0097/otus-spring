package ru.diasoft.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {
    private String name;
    private String alpha2Code;
    private String alpha3Code;
}
