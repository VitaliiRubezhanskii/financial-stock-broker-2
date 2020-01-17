package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.Order;
import com.investment.feign_hystrix.integration.domain.OrderItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntegrationOrderController {

//    private final IntegrationClient integrationClient;

    private final WebClient.Builder webClientBuilder;


    @GetMapping(value = "/order/samples")
    @PreAuthorize("hasRole('ROLE_USER')")
//    @HystrixCommand(fallbackMethod = "getDefaultExample")
    public Flux<Order> getExample() {
        return webClientBuilder.build().get().uri("http://order:8082/order/sample").retrieve().bodyToFlux(Order.class);
//        return integrationClient.getExample();
    }


//    @GetMapping(value = "/order/{id}")
//    @HystrixCommand(fallbackMethod = "getDef")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public Flux<Order> getOrders(@PathVariable("id") String id){
//        return integrationClient.getOrders(id);
//    }

 //=============================================================================================================

//    public Order getDef(String id){
//        List<OrderItem> items = new ArrayList<>();
//        items.add(new OrderItem("sku_1-default", "default-port"));
//        items.add(new OrderItem("sku_2-default", "default-port"));
//        return Mono.just(new Order(items, "Invoice", LocalDateTime.now())).block();
//    }
//
//    public Order getDefaultExample(){
//        List<OrderItem>  items = new ArrayList<>();
//        items.add(new OrderItem("sku_1-samples", "default-port"));
//        items.add(new OrderItem("sku_2-samples", "default-port"));
//        return Mono.just(new Order(items,"Invoice", LocalDateTime.now())).block();
//    }
//
//    public Order createDefault(Order order){
//        List<OrderItem>  items = new ArrayList<>();
//        items.add(new OrderItem("sku_1-error", "default-port"));
//        items.add(new OrderItem("sku_2-error", "default-port"));
//        return Mono.just(new Order(items,"Invoice", LocalDateTime.now())).block();
//    }
}
