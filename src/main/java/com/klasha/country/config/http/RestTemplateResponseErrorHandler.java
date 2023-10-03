package com.klasha.country.config.http;

import com.klasha.country.exceptions.ServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(final ClientHttpResponse httpResponse) throws IOException {
        log.error("Response error: {} {}", httpResponse.getStatusCode(), httpResponse.getStatusText());
        return (httpResponse.getStatusCode().value() == CLIENT_ERROR.value()
                || httpResponse.getStatusCode().value() == SERVER_ERROR.value());
    }

    @Override
    public void handleError(final ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().value() == HttpStatus.Series.SERVER_ERROR.value()) {
            // handle SERVER_ERROR
            throw new ServerErrorException("Unable to connect to vendors server");
        }
    }
}
