package com.hw.webflux.webflux.domain;

import lombok.Data;

@Data
public class Sport {

    private String id;
    private String name;

    public Sport(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
