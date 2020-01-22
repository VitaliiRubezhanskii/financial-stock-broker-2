package com.investment.trading;

import com.investment.trading.kafka.avro.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSchemaRegistryClient
//@EnableBinding(Processor.class)
public class TradingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradingApplication.class, args);
    }

    @Autowired
    private Processor source;

//    @Bean
//    public WebClient client() {
//        return WebClient.create("http://localhost:8082");
//    }
//
//    @Bean
//    public CommandLineRunner demo(WebClient client) {
//        return args -> {
//            client.get()
//                    .uri("/events")
//                    .accept(MediaType.TEXT_EVENT_STREAM)
//                    .exchange()
//                    .flatMapMany(cr -> cr.bodyToFlux(OrderRequest.class))
//                    .subscribe(quote -> source.output().send(MessageBuilder.withPayload(quote).build()));
//        };
//    }
}
