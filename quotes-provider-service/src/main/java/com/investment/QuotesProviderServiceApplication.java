package com.investment;


import com.example.Sensor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Supplier;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class QuotesProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuotesProviderServiceApplication.class, args);
    }


    @Bean
    public Supplier<Sensor> process() {
       return () -> {
           String id = "7";
//           return new Quote(id, "2021-10-19", "MSFT","10","15","14","100","9");
            Sensor sensor =  new Sensor(id, 2, 2, 2);
//
//           return MessageBuilder.withPayload(sensor)
//                   .setHeader("key", id)
//                   .build();

           return sensor;
       };
    }

}

