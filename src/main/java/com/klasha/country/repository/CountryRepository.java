package com.klasha.country.repository;

import com.klasha.country.dal.Country;
import com.klasha.country.dtos.CountryDetailsDto;
import com.klasha.country.projections.CountryCurrencyProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.klasha.country.projections.CountryProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {

    @Query(value = "SELECT * FROM countries WHERE LOWER(name) IN (:countryNames)", nativeQuery = true)
    List<Country> findCountriesByName(@Param("countryNames") List<String> countryNames);

    @Query(value = "SELECT c.name, c.iso3 as iso3, c.country_code as iso2, c.capital, cu.currency as currency, SUM(ci.population) as population " +
            "FROM countries c " +
            "LEFT JOIN cities ci ON ci.country_id=c.id " +
            "INNER JOIN currencies cu ON cu.country_code=c.country_code " +
            "WHERE LOWER(c.name) = LOWER(:countryName)", nativeQuery = true)
    CountryProjection getCountryDetails(@Param("countryName") String countryName);

    @Query(value = "SELECT c.name as countryName, cu.currency_code as currency " +
            "FROM countries c " +
            "INNER JOIN currencies cu ON cu.country_code=c.country_code " +
            "WHERE LOWER(c.name) = LOWER(:countryName)", nativeQuery = true)
    CountryCurrencyProjection getCountry(@Param("countryName") String country);


}
