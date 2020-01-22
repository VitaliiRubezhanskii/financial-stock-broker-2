package com.investment.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSchemaRegistryClient
//@EnableBinding(Processor.class)
public class TradingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradingApplication.class, args);
    }



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
