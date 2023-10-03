package com.klasha.country.projections;

import java.math.BigDecimal;

public interface CountryProjection {
    String getName();
    BigDecimal getPopulation();

    String getCapital();

    String getCurrency();

    String getIso2();

    String getIso3();
}
