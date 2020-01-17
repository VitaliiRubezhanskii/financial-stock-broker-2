package com.investment.feign_hystrix.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntegrationClient {

//    private final AccountServiceClient accountServiceClient;

//    private final OrderServiceClient orderServiceClient;


//    @HystrixCommand(fallbackMethod = "getDefaultOrders")
//    public Flux<Order> getOrders(String id){
//        return orderServiceClient.get(id);
//    }

//    @HystrixCommand(fallbackMethod = "getDefaultExample")
//    public Flux<Order> getExample(){
//        return orderServiceClient.getExample();
//    }


//    public Order create(Order order){
//        return orderServiceClient.create(order);
//    }
//
//
////    @HystrixCommand(fallbackMethod = "getDefaultHello")
//    public String getHello(){
//        return accountServiceClient.getHello();
//    }

}
