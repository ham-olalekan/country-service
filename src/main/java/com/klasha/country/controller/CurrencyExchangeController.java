package com.klasha.country.controller;

import com.klasha.country.dtos.ResponseDto;
import com.klasha.country.exceptions.GenericException;
import com.klasha.country.service.IExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CurrencyExchangeController {

    private final IExchangeRateService exchangeRateService;

    @GetMapping("exchange")
    public ResponseDto<?> handleExchangeRate(@RequestParam() String country,
                                             @RequestParam() String target,
                                             @RequestParam() BigDecimal amount) throws GenericException {
        return ResponseDto.wrapSuccessResult(exchangeRateService.getExchangeValue(country, amount, target), "successful");
    }
}
