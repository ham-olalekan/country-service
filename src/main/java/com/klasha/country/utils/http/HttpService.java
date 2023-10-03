package com.klasha.country.utils.http;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface HttpService {
    <T> ResponseEntity<T> get(UriComponentsBuilder builder,
                              HttpHeaders httpHeaders,
                              Class<T> responseType);

    <T, R> ResponseEntity<T> post(UriComponentsBuilder builder, HttpHeaders httpHeaders,
                                  R requestBody, Class<T> responseType);


    <T, R> ResponseEntity<T> post(UriComponentsBuilder builder, HttpHeaders httpHeaders,
                                  R requestBody, ParameterizedTypeReference<T> responseType);

    <T, R> ResponseEntity<T> get(UriComponentsBuilder builder, HttpHeaders httpHeaders,
                                 ParameterizedTypeReference<T> responseType);
}
