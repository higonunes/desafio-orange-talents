package com.orangetalents.desafio.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class ApiService {

    public static RestTemplate apiFipe() {
        return new RestTemplateBuilder()
                .uriTemplateHandler(new DefaultUriBuilderFactory("https://parallelum.com.br/fipe/api/v1"))
                .build();
    }
}
