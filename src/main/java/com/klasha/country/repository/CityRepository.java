package com.klasha.country.repository;

import com.klasha.country.dal.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    @Query(value = "SELECT * FROM cities ORDER BY population DESC LIMIT :count", nativeQuery = true)
    List<City> getMostPopulatedCities(@Param("count")int count);

    @Query(value = "SELECT * FROM cities ORDER BY population DESC", nativeQuery = true)
    List<City> getAllCities();

}
