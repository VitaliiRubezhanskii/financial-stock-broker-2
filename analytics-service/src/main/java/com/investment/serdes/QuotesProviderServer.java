package com.investment.serdes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class QuotesProviderServer {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping(value = "/health")
    public List<String> health() {
        return Collections.singletonList("HEALTHY!!!");
    }


}
