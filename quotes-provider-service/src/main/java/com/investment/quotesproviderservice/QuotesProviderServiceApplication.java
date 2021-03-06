package com.investment.quotesproviderservice;

import avro.Quote;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Source.class)
@RestController
public class QuotesProviderServiceApplication {

    @Autowired
    private Source source;

    public static void main(String[] args) {
        SpringApplication.run(QuotesProviderServiceApplication.class, args);
    }

//    @Bean
//    public WebClient client() {
//        return WebClient.create("http://localhost:9010");
//    }

    @Bean
    public CommandLineRunner demo() {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
//            List v = Collections.emptyList();
//            try{
//                v =   mapper.readValue(QuotesProviderServiceApplication.class.getResourceAsStream("/data.json"), List.class);
//            }catch (IOException ex){
//                ex.printStackTrace();
//            }
//            client.get()
//                    .uri("/events")
//                    .accept(MediaType.TEXT_EVENT_STREAM)
//                    .exchange()
//                    .flatMapMany(cr -> cr.bodyToFlux(Quote.class))
//                    .subscribe(quote ->
//                            source.output().send(MessageBuilder.withPayload(v).build());
        };
    }



//    @RequestMapping(value = "/messages", method = RequestMethod.POST)
//    public String sendMessage() {
//        source.output().send(MessageBuilder.withPayload(randomSensor()).build());
//        return "ok, have fun with v2 payload!";
//    }
//
//    private Quote randomSensor() {
//        Quote quote = new Quote();
//        quote.setId("1");
////        quote.setOpen(54.0f);
////        quote.setClose(54.0f);
////        quote.setHigh(59.0f);
////        quote.setLow(50.9f);
//        quote.setTicket("MSFT");
//        return quote;
//    }
}
