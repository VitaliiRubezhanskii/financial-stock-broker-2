package com.investment;


import com.investment.avro.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.schema.registry.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient;
import org.springframework.cloud.schema.registry.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSchemaRegistryClient
@RequiredArgsConstructor
@EnableCaching
public class QuotesProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuotesProviderServiceApplication.class, args);
    }


    @Bean
    public Supplier<Message<Quote>> process() {
       return () -> {
           String id = "05b0d215-0101-4442-a4da-4f2b423cdacf";
//           return new Quote(id, "2021-10-19", "MSFT","10","15","14","100","9");

           return MessageBuilder.withPayload(new Quote(id, "2021-10-19", "MSFT","10","15","14","100","9"))
                   .setHeader(KafkaHeaders.MESSAGE_KEY, id)
                   .build();
       };
    }
}

