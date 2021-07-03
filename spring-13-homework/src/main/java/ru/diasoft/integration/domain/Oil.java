package ru.diasoft.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Oil {
    private String producerCountry;
    private String type;
    private BigDecimal cost;
}
