package com.hw.webflux.webflux.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
public class SportsApiRouter {

    @Bean
    public RouterFunction<ServerResponse> routing(SportsApiHandler sportApiHandler) {
        return route()
                .path("/api/v2/sport", builder -> builder
                        .GET("", request -> request.queryParam("q").isPresent(), sportApiHandler::getByName)
                        .POST("/{sportname}", accept(APPLICATION_JSON), sportApiHandler::createSport))
                .build();
    }
}