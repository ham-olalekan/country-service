package com.klasha.country.dtos;

import com.klasha.country.projections.CountryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDetailsDto {
    private String name;
    private String iso3;
    private String iso2;
    private String capital;
    private String currency;
    private BigDecimal population;

    public static CountryDetailsDto fromProjection(CountryProjection projection){
        CountryDetailsDto dto = new CountryDetailsDto();
        dto.setName(projection.getName());
        dto.setIso2(projection.getIso2());
        dto.setIso3(projection.getIso3());
        dto.setCapital(projection.getCapital());
        dto.setCurrency(projection.getCurrency());
        return dto;
    }
}
