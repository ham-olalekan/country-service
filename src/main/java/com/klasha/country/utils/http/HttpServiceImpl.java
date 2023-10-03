package com.klasha.country.utils.http;

import com.klasha.country.config.http.RequestResponseLoggingInterceptor;
import com.klasha.country.config.http.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class HttpServiceImpl implements HttpService {

    private final RestTemplate restTemplate;

    @Autowired
    public HttpServiceImpl(final RestTemplateBuilder restTemplateBuilder,
                           final RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        this.restTemplate = restTemplateBuilder
                .requestFactory(() -> factory)
                .errorHandler(restTemplateResponseErrorHandler)
                .interceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()))
                .build();
    }

    public <T> ResponseEntity<T> get(UriComponentsBuilder builder, HttpHeaders httpHeaders,
                                     Class<T> responseType) {

        final HttpEntity<HttpHeaders> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, responseType);
    }

    public <T, R> ResponseEntity<T> post(UriComponentsBuilder builder, HttpHeaders httpHeaders,
                                         R requestBody, Class<T> responseType) {

        final HttpEntity<R> entity = new HttpEntity<>(requestBody, httpHeaders);
        return restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, entity, responseType);
    }

    public <T, R> ResponseEntity<T> post(UriComponentsBuilder builder, HttpHeaders httpHeaders,
                                         R requestBody, ParameterizedTypeReference<T> responseType) {

        final HttpEntity<R> entity = new HttpEntity<>(requestBody, httpHeaders);
        return restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, entity, responseType);
    }

    public <T, R> ResponseEntity<T> get(UriComponentsBuilder builder, HttpHeaders httpHeaders,
                                          ParameterizedTypeReference<T> responseType) {

        final HttpEntity<R> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, responseType);
    }
}
