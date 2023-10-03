package com.klasha.country.repository;

import com.klasha.country.dal.State;
import com.klasha.country.projections.CountryStateProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {
    @Query(value = "SELECT s.name, s.code FROM states s" +
            " INNER JOIN countries c on c.iso3=s.country_iso3 "+
            " WHERE s.country_iso3=:iso3", nativeQuery = true
    )
    List<CountryStateProjection> getStatesByCountry(@Param("iso3") String iso3);
}
