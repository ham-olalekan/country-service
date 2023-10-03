package com.klasha.country.service;

import com.klasha.country.clients.CountryPopulationClient;
import com.klasha.country.clients.response.CountryPopulationResponse;
import com.klasha.country.clients.response.GenericSingleFilterResponse;
import com.klasha.country.dtos.CityDto;
import com.klasha.country.dtos.CountryDetailsDto;
import com.klasha.country.exceptions.GenericException;
import com.klasha.country.projections.CountryProjection;
import com.klasha.country.projections.CountryStateProjection;
import com.klasha.country.repository.CityRepository;
import com.klasha.country.repository.CountryRepository;
import com.klasha.country.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService implements ICountryService {

    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    private final StateRepository stateRepository;

    private final CountryPopulationClient client;

    @Override
    public List<CityDto> getCountryPopulation(int count) {
        long totalCityCount = cityRepository.count();

        if (count <= totalCityCount) {
            return CityDto.map(cityRepository
                    .getMostPopulatedCities(count));
        }

        return CityDto.map(cityRepository.getAllCities());
    }

    @Override
    public CountryDetailsDto getCountryDetails(String name) throws GenericException {
        CountryProjection projection = countryRepository.getCountryDetails(name);
        if (projection.getName() == null) throw new GenericException(String.format("unknown country %s", name), HttpStatus.NOT_FOUND);

        GenericSingleFilterResponse<CountryPopulationResponse> populationResp = client.getCountryPopulation(name);

        CountryDetailsDto dto = CountryDetailsDto.fromProjection(projection);
        int size = populationResp.getData().getPopulationCounts().size();
        dto.setPopulation(populationResp.getData().getPopulationCounts().get(size-1).getValue());
        return dto;
    }

    @Override
    public List<CountryStateProjection> getAllStates(String country) throws GenericException {
        CountryProjection projection = countryRepository.getCountryDetails(country);
        if (projection.getName() == null) throw new GenericException(String.format("unknown country %s", country), HttpStatus.NOT_FOUND);
        return stateRepository.getStatesByCountry(projection.getIso3());
    }
}
