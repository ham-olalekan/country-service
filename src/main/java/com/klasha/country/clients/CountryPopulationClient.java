package com.klasha.country.clients;

import com.klasha.country.clients.response.CityPopulationsCountResponse;
import com.klasha.country.clients.response.CountryPopulationResponse;
import com.klasha.country.clients.response.CountryStateResponse;
import com.klasha.country.clients.response.GenericSingleFilterResponse;
import com.klasha.country.clients.response.GenericfilterResponse;
import com.klasha.country.exceptions.GenericException;
import com.klasha.country.utils.http.HttpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryPopulationClient {

    private final HttpService httpService;

    private final String baseURL = "https://countriesnow.space/";


    public GenericfilterResponse<CityPopulationsCountResponse> getCountryCityPopulation(String country) throws GenericException {
        ParameterizedTypeReference<GenericfilterResponse<CityPopulationsCountResponse>> responseType =
                new ParameterizedTypeReference<GenericfilterResponse<CityPopulationsCountResponse>>() {
                };
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.baseURL + "api/v0.1/countries/population/cities/filter/q");
        builder.queryParam("country", country);
        ResponseEntity<GenericfilterResponse<CityPopulationsCountResponse>> responseEntity = httpService.get(builder, headers, responseType);
        if (responseEntity.getStatusCode().is2xxSuccessful()) return responseEntity.getBody();
        throw new GenericException("CountryPopulationClient.getCountryPopulation failed due to error: " + responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public GenericfilterResponse<CountryStateResponse> getCountryStates() throws GenericException {
        ParameterizedTypeReference<GenericfilterResponse<CountryStateResponse>> responseType =
                new ParameterizedTypeReference<GenericfilterResponse<CountryStateResponse>>() {
                };
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.baseURL + "api/v0.1/countries/states");
        ResponseEntity<GenericfilterResponse<CountryStateResponse>> responseEntity = httpService.get(builder, headers, responseType);
        if (responseEntity.getStatusCode().is2xxSuccessful()) return responseEntity.getBody();
        throw new GenericException("CountryPopulationClient.getCountryStates failed due to error: " + responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public GenericSingleFilterResponse<CountryPopulationResponse> getCountryPopulation(String country) throws GenericException {
        ParameterizedTypeReference<GenericSingleFilterResponse<CountryPopulationResponse>> responseType =
                new ParameterizedTypeReference<GenericSingleFilterResponse<CountryPopulationResponse>>() {
                };
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.baseURL + "/api/v0.1/countries/population/q");
        builder.queryParam("country", country);
        ResponseEntity<GenericSingleFilterResponse<CountryPopulationResponse>> responseEntity = httpService.get(builder, headers, responseType);
        if (responseEntity.getStatusCode().is2xxSuccessful()) return responseEntity.getBody();
        throw new GenericException("CountryPopulationClient.getCountryStates failed due to error: " + responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
