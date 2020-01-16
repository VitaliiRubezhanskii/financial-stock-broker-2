package com.investment.quotesproviderservice.server;


import avro.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.investment.quotesproviderservice.QuotesProviderServiceApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@RestController
public class QuotesProviderServer {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/events")
    public Flux<Quote> events() {
        ObjectMapper mapper = new ObjectMapper();
        List<Quote> v = Collections.emptyList();
        try{
            v =   mapper.readValue(QuotesProviderServiceApplication.class.getResourceAsStream("/data.json"), List.class);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        Flux<Quote> eventFlux = Flux.fromStream(v.stream());

        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(eventFlux, durationFlux).map(Tuple2::getT1);
    }

}
