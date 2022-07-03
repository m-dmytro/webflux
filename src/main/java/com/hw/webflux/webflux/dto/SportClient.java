package com.hw.webflux.webflux.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SportClient {

    private String id;
    private String type;
    private Map<String, Object> attributes;

}
