package com.investment.quotesproviderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QuotesProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuotesProviderServiceApplication.class, args);
    }

}
