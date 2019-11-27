package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.client.AccountServiceClient;
import com.investment.feign_hystrix.integration.client.OrderServiceClient;
import com.investment.feign_hystrix.integration.domain.Order;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class IntegrationClient {

    private final AccountServiceClient accountServiceClient;

    private final OrderServiceClient orderServiceClient;


//    @HystrixCommand(fallbackMethod = "getDefaultOrders")
    public Order getOrders(String id){
        return orderServiceClient.get(id);
    }

//    @HystrixCommand(fallbackMethod = "getDefaultExample")
    public Order getExample(){
        return orderServiceClient.getExample();
    }


    public Order create(Order order){
        return orderServiceClient.create(order);
    }


//    @HystrixCommand(fallbackMethod = "getDefaultHello")
    public String getHello(){
        return accountServiceClient.getHello();
    }

}
