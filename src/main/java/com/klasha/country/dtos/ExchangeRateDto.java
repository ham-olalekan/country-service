package com.klasha.country.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
public class ExchangeRateDto {
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;
}
