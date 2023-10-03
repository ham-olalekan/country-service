package com.klasha.country.clients.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericfilterResponse<T> {
    private boolean error;

    private boolean message;

    private List<T> data;
}
