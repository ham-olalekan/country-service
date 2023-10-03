package com.klasha.country.events;

import com.klasha.country.clients.CountryPopulationClient;
import com.klasha.country.clients.response.CityPopulationsCountResponse;
import com.klasha.country.clients.response.CountryStateResponse;
import com.klasha.country.clients.response.GenericfilterResponse;
import com.klasha.country.dal.City;
import com.klasha.country.dal.Country;
import com.klasha.country.dal.State;
import com.klasha.country.exceptions.GenericException;
import com.klasha.country.repository.CityRepository;
import com.klasha.country.repository.CountryRepository;
import com.klasha.country.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSync implements ApplicationListener<ApplicationEvent> {

    private final CountryRepository repository;

    private final CountryPopulationClient client;

    private final CityRepository cityRepository;

    private final StateRepository stateRepository;

    private static final List<String> DEFAULT_COUNTRIES = Arrays.asList("new zealand", "italy", "ghana");

    @Override
    public void onApplicationEvent(@NonNull ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            ForkJoinPool.commonPool().execute(() -> {
                syncStateForCountries();
                syncDefaultCountriesPopulation();
            });
        }
    }


    private void syncDefaultCountriesPopulation() {
        List<Country> countries = repository.findCountriesByName(DEFAULT_COUNTRIES);
        countries.forEach(country -> {
            GenericfilterResponse<CityPopulationsCountResponse> response = null;
            try {
                response = client.getCountryCityPopulation(country.getName());
                if (response.isError())
                    log.error("failed to sync country population for {} due to error:{}", country.getName(), response.isMessage());
                else {
                    Set<City> citySet = new HashSet<>();
                    response.getData().parallelStream().forEach(city -> citySet.add(mapResponseToCity(country, city)));
                    cityRepository.saveAll(citySet);
                }
            } catch (GenericException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private City mapResponseToCity(Country country, CityPopulationsCountResponse populationsCountResponse) {
        City city = new City();
        city.setName(populationsCountResponse.getCity());
        city.setPopulation(new BigDecimal(populationsCountResponse.getPopulationCounts().get(0).getValue()));
        city.setCountryId(country.getId());
        city.setCountryName(country.getName());
        return city;
    }

    private void mapResponseToState(List<CountryStateResponse> countryStateResponse) {
        Set<State> stateSet = new HashSet<>();
        countryStateResponse
                .forEach(cs -> {
                    cs.getStates().forEach(s -> {
                        State state = new State();
                        state.setCountryName(cs.getName());
                        state.setCountryIso3(cs.getIso3());
                        state.setName(s.getName());
                        state.setCode(s.getCode());
                        stateSet.add(state);
                    });
                });
        stateRepository.saveAll(stateSet);
    }

    private void syncStateForCountries() {
        try {
            GenericfilterResponse<CountryStateResponse> response = client.getCountryStates();
            if (response.isError())
                log.error("failed to sync country states due to error:{}", response.isMessage());
            else {
                mapResponseToState(response.getData());
            }
        } catch (GenericException ex) {
            log.error("syncStateForCountry error: {}", ex.getMessage());
        }
    }

}
