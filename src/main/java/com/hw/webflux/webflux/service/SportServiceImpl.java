package com.hw.webflux.webflux.service;

import com.hw.webflux.webflux.domain.Sport;
import com.hw.webflux.webflux.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SportServiceImpl implements ISportService {

    private final SportRepository repository;

    @Override
    public Flux<Sport> getAll() {
        return repository.findAll();
    }

//    @Override
//    public Mono<Sport> getByName(String name) {
//        return repository.findByName(name);
//    }

    @Override
    public Mono<ResponseEntity> getByName(String name) {
        return repository.findByName(name)
                .map(sport -> ResponseEntity.status(HttpStatus.OK).body(sport))
                .cast(ResponseEntity.class)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sport " + name + " is not present"));
    }

    @Override
    public Flux<Sport> saveAll(Flux<Sport> sports) {
        return repository.saveAll(sports)
                .onErrorContinue((ex, skip) -> log.error("Error during saving data, such data is present"));
    }

    @Override
    public Mono<ResponseEntity> createSport(String sportName) {
        return repository
                .save(new Sport(UUID.randomUUID().toString(), sportName))
                .map(sport -> ResponseEntity.status(HttpStatus.OK).body(sport))
                .cast(ResponseEntity.class)
                .onErrorReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Such sport is present"));
    }

}
