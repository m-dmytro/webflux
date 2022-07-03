package com.hw.webflux.webflux.service;

import com.hw.webflux.webflux.domain.Sport;
import reactor.core.publisher.Flux;

public interface ISportsApiClient {

    Flux<Sport> getAllSportData();

}
