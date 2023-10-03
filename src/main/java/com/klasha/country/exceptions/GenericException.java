package com.klasha.country.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class GenericException extends Exception {
    private final String message;
    private final HttpStatus httpStatus;
}
