package com.investment.server;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.investment.QuotesProviderServiceApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class QuotesProviderServer {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping(value = "/events")
    public List<String> trigger() {
        return new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                        QuotesProviderServiceApplication.class.getResourceAsStream("/strings.txt"))
                ))
                .lines().collect(Collectors.toList());
    }


}
