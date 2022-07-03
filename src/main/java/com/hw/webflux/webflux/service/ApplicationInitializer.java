package com.hw.webflux.webflux.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationInitializer implements ApplicationRunner {

    private final ISportsApiClient sportsApiClient;
    private final ISportService sportService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sportService.saveAll(sportsApiClient.getAllSportData())
                .blockLast(); // to terminate the chain of execution
    }
}
