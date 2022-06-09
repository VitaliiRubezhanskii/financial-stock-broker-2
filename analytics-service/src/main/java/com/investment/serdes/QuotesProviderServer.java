package com.investment.serdes;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class QuotesProviderServer {

    @GetMapping(value = "/health")
    public List<String> health() {
        return Collections.singletonList("HEALTHY!!!");
    }
}
