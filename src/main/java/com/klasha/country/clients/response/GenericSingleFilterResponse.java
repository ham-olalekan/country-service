package com.klasha.country.clients.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericSingleFilterResponse <T> {
    private boolean error;

    private boolean message;

    private T data;
}