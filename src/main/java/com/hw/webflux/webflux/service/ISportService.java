package com.hw.webflux.webflux.service;

import com.hw.webflux.webflux.domain.Sport;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISportService {

    Flux<Sport> getAll();

//    Mono<Sport> getByName(String name);
    Mono<ResponseEntity> getByName(String name);

    Flux<Sport> saveAll(Flux<Sport> sports);

    Mono<ResponseEntity> createSport(String sportName);

}
