package com.klasha.country.service;

import com.klasha.country.dtos.ExchangeRateDto;
import com.klasha.country.exceptions.GenericException;

import java.math.BigDecimal;

public interface IExchangeRateService {

    ExchangeRateDto getExchangeValue(String country, BigDecimal amount, String targetCurrency) throws GenericException;
}
