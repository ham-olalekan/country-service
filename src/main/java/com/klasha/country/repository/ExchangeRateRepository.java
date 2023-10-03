package com.klasha.country.repository;

import com.klasha.country.dal.ExchangeRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {

    @Query(value = "SELECT * FROM exchange_rates WHERE source_currency_code = :sourceCurrency AND destination_currency_code = :targetCurrency", nativeQuery = true)
    Optional<ExchangeRate> getExchangeRate(@Param("sourceCurrency") String sourceCurrency, @Param("targetCurrency") String targetCurrency);

}
