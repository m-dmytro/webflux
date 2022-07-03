package com.hw.webflux.webflux.repository;

import com.hw.webflux.webflux.domain.Sport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SportRepository extends ReactiveCrudRepository<Sport, String> {

    Mono<Sport> findByName(String name);

}
