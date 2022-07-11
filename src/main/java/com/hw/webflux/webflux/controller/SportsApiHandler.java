package com.hw.webflux.webflux.controller;

import com.hw.webflux.webflux.domain.Sport;
import com.hw.webflux.webflux.repository.SportRepository;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SportsApiHandler {

    private final SportRepository repository;

    public Mono<ServerResponse> getByName(ServerRequest request) {
        var sportNameOpt = request.queryParam("q");
        if (sportNameOpt.isEmpty() || StringUtil.isNullOrEmpty(sportNameOpt.get())) {
            return ServerResponse.badRequest().build();
        }

        return repository.findByName(sportNameOpt.get())
                .flatMap(sport -> ServerResponse.ok().bodyValue(sport))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createSport(ServerRequest request) {
        var sportName = request.pathVariable("sportname");
        if (StringUtil.isNullOrEmpty(sportName)) {
            return ServerResponse.badRequest().build();
        }

        return repository
                .save(new Sport(UUID.randomUUID().toString(), sportName))
                .flatMap(sport -> ServerResponse.ok().bodyValue(sport))
                .onErrorReturn(Objects.requireNonNull(ServerResponse.badRequest().build().block()));
    }

}