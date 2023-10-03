package com.klasha.country.controller;

import com.klasha.country.dtos.ResponseDto;
import com.klasha.country.exceptions.GenericException;
import com.klasha.country.service.ICountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CountryController {

    private final ICountryService populationService;

    @GetMapping("population/cities")
    public ResponseDto<?> handleListingMostPopulousCities(@RequestParam() int n) {
        return ResponseDto.wrapSuccessResult(populationService.getCountryPopulation(n), "successful");
    }

    @GetMapping("country")
    public ResponseDto<?> handleGettingCountryDetails(@RequestParam("countryName") String countryName) throws GenericException {
        return ResponseDto.wrapSuccessResult(populationService.getCountryDetails(countryName), "successful");
    }

    @GetMapping("country/states")
    public ResponseDto<?> handleGettingCountryStates(@RequestParam("countryName") String countryName) throws GenericException {
        return ResponseDto.wrapSuccessResult(populationService.getAllStates(countryName), "successful");
    }
}
