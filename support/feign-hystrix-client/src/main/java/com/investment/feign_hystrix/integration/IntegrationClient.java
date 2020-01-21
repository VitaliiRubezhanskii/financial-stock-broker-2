package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.OrderCreatedDto;
import com.investment.feign_hystrix.integration.domain.OrderCreationDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntegrationClient {

    private final AccountServiceClient accountServiceClient;

    private final OrderServiceClient orderServiceClient;


    @HystrixCommand(fallbackMethod = "getDefaultOrders")
    public OrderCreationDto getOrders(String id){
        return orderServiceClient.get(id);
    }

    @HystrixCommand(fallbackMethod = "getDefaultExample")
    public OrderCreationDto getExample(){
        return orderServiceClient.getExample();
    }

    public OrderCreatedDto create(OrderCreationDto orderCreationDto){
        return orderServiceClient.create(orderCreationDto);
    }

    @HystrixCommand(fallbackMethod = "getDefaultHello")
    public String getHello(){
        return accountServiceClient.getHello();
    }

}
