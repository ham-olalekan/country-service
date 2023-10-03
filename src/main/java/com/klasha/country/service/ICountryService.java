package com.klasha.country.service;

import com.klasha.country.dtos.CityDto;
import com.klasha.country.dtos.CountryDetailsDto;
import com.klasha.country.exceptions.GenericException;
import com.klasha.country.projections.CountryStateProjection;

import java.util.List;

public interface ICountryService {
    List<CityDto> getCountryPopulation(int count);

    CountryDetailsDto getCountryDetails(String name) throws GenericException;

    List<CountryStateProjection> getAllStates(String country) throws GenericException;
}
