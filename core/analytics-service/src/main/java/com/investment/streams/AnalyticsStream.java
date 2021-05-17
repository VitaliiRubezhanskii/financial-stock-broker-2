package com.investment.streams;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class AnalyticsStream {

    @Bean
    public Function<String, String> process() {
       return value -> {
            System.out.println(value);
            return value.toUpperCase();
        };
    }
}
