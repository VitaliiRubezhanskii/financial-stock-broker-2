package com.investment.quotesproviderservice.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.investment.avro.Quote;
import com.investment.quotesproviderservice.QuotesProviderServiceApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuotesProviderServer {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/events")
    public Flux<Quote> events() {
        ObjectMapper mapper = new ObjectMapper();
        List v = Collections.emptyList();
        try{
            v =   mapper.readValue(QuotesProviderServiceApplication.class.getResourceAsStream("/data.json"), List.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }
//        List<Quote> quotes = v.stream().map(e->gson.fromJson(e.toString(), LinkedHashMap.class));
        Flux<Quote> eventFlux = Flux.fromStream(v.stream().map(e->gson.fromJson(gson.toJson(e), LinkedHashMap.class)));

        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);

    }

}
