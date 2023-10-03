package com.klasha.country.dtos;

import com.klasha.country.dal.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
public class CityDto {
    private String name;

    private String countryName;

    private BigDecimal population;

    public static CityDto toCityDto(City city) {
        return CityDto
                .builder()
                .countryName(city.getCountryName())
                .name(city.getName())
                .population(city.getPopulation())
                .build();
    }

    public static List<CityDto> map(List<City> cities) {
        return cities.stream()
                .map(CityDto::toCityDto)
                .collect(Collectors.toList());
    }

    public static List<CityDto> map(Iterable<City> cities) {
        List<CityDto> cityDtos = new ArrayList<>();
        cities.forEach(city -> cityDtos.add(toCityDto(city)));
        return cityDtos;
    }
}
