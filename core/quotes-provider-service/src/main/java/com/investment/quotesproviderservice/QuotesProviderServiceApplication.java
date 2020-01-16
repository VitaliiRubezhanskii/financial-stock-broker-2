package com.investment.quotesproviderservice;

import avro.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Source.class)
@RestController
public class QuotesProviderServiceApplication {

    @Autowired
    private Source source;

    private Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(QuotesProviderServiceApplication.class, args);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public String sendMessage() {
        source.output().send(MessageBuilder.withPayload(randomSensor()).build());
        return "ok, have fun with v2 payload!";
    }

    private Quote randomSensor() {
        Quote quote = new Quote();
        quote.setId("1");
//        quote.setOpen(54.0f);
//        quote.setClose(54.0f);
//        quote.setHigh(59.0f);
//        quote.setLow(50.9f);
        quote.setTicket("MSFT");
        return quote;
    }
}
