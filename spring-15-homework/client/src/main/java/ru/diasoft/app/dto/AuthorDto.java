package ru.diasoft.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDto {
    private Long authorId;
    private String firstName;
    private String lastName;
    private String middleName;
}
