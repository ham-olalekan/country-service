package com.klasha.country.service;

import com.klasha.country.dal.ExchangeRate;
import com.klasha.country.dtos.ExchangeRateDto;
import com.klasha.country.exceptions.GenericException;
import com.klasha.country.projections.CountryCurrencyProjection;
import com.klasha.country.repository.CountryRepository;
import com.klasha.country.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class IExchangeRateServiceImpl implements IExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    private final CountryRepository countryRepository;

    @Override
    public ExchangeRateDto getExchangeValue(String country, BigDecimal amount, String targetCurrency) throws GenericException {

        CountryCurrencyProjection countryCurrencyProjection = countryRepository.getCountry(country);
        if (Objects.isNull(countryCurrencyProjection.getCurrency()))
            throw new GenericException(String.format("country with name %s was not found", country), HttpStatus.NOT_FOUND);
        ExchangeRate rate = exchangeRateRepository
                .getExchangeRate(countryCurrencyProjection.getCurrency(), targetCurrency)
                .orElseThrow(() -> new GenericException(String.format("no exchange rate for %s - %s combination was not found", countryCurrencyProjection.getCurrency(), targetCurrency), HttpStatus.NOT_FOUND));

        System.out.println("=>"+rate);
        BigDecimal targetAmount = amount.multiply(rate.getRate()).setScale(2, RoundingMode.UNNECESSARY);
        return ExchangeRateDto
                .builder()
                .sourceAmount(amount)
                .sourceCurrency(countryCurrencyProjection.getCurrency())
                .targetCurrency(targetCurrency)
                .targetAmount(targetAmount)
                .build();
    }
}
