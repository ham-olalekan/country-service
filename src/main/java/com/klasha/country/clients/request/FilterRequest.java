package com.klasha.country.clients.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class FilterRequest {

    @JsonProperty("country")
    private String country;

}
