package com.hw.webflux.webflux.controller;

import com.hw.webflux.webflux.domain.Sport;
import com.hw.webflux.webflux.service.ISportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sport")
@RequiredArgsConstructor
public class MainController {

    private final ISportService service;

    @GetMapping("/all")
    public Flux<Sport> getAll() {
        return service.getAll();
    }

    @GetMapping
//    public Mono<Sport> getById(@RequestParam("q") String sportName) {
    public Mono<ResponseEntity> getById(@RequestParam("q") String sportName) {
        return service.getByName(sportName);
    }

    @PostMapping("/{sportname}")
    public Mono<ResponseEntity> create(@PathVariable("sportname") String sportname) {
        return service.createSport(sportname);
    }

}
