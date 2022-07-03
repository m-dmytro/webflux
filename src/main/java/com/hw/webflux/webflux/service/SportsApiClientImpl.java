package com.hw.webflux.webflux.service;

import com.hw.webflux.webflux.domain.Sport;
import com.hw.webflux.webflux.dto.SportsDataClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SportsApiClientImpl implements ISportsApiClient {

    private final WebClient webClient;

    @Override
    public Flux<Sport> getAllSportData() {
        return webClient
                .get()
                .uri("/sports")
                .exchangeToMono(resp -> resp.bodyToMono(SportsDataClient.class))
                .doOnError(error -> log.error("Error during getting data from sports endpoint ", error))
                .doOnNext(data -> log.info("sports data: {}", data))
                .flatMapIterable(sportsDataClient -> sportsDataClient.getData().stream()
                        .map(sportClient -> new Sport(sportClient.getId(), (String) sportClient.getAttributes().get("name")))
                        .collect(Collectors.toList()));
    }

}
