package com.klasha.country.config;


import io.netty.handler.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;
import reactor.netty.http.client.HttpClient;


@Configuration
public class WebclientConfig {

    @Bean
    public WebClient registerWebClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create().wiretap("reactor.netty.http.client.HttpClient",
                LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
        builder.clientConnector(new ReactorClientHttpConnector(httpClient));
        return builder.build();
    }
}
