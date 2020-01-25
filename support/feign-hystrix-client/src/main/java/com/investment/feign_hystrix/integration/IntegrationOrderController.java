package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.orders.OrderCreatedDto;
import com.investment.feign_hystrix.integration.domain.orders.OrderCreationDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class IntegrationOrderController {

    private final IntegrationClient integrationClient;

    @GetMapping(value = "/order/samples")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(fallbackMethod = "getDefaultExample")
    public OrderCreationDto getExample() {
        return integrationClient.getExample();
    }

    @GetMapping(value = "/order/{id}")
    @HystrixCommand(fallbackMethod = "getDef")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public OrderCreationDto getOrders(@PathVariable("id") String id){
        return integrationClient.getOrders(id);
    }

    @PostMapping
    public OrderCreatedDto create(@RequestBody OrderCreationDto orderCreationDto){
        return integrationClient.create(orderCreationDto);
    }

// =============================================================================================================

//    public OrderCreationDto getDef(String id){
//        List<OrderItem> items = new ArrayList<>();
//        items.add(new OrderItem("sku_1-default", "default-port"));
//        items.add(new OrderItem("sku_2-default", "default-port"));
//        return Mono.just(new OrderCreationDto(items, "Invoice", LocalDateTime.now())).block();
//    }
//
//    public OrderCreationDto getDefaultExample(){
//        List<OrderItem>  items = new ArrayList<>();
//        items.add(new OrderItem("sku_1-samples", "default-port"));
//        items.add(new OrderItem("sku_2-samples", "default-port"));
//        return Mono.just(new OrderCreationDto(items,"Invoice", LocalDateTime.now())).block();
//    }
//
//    public OrderCreationDto createDefault(OrderCreationDto orderCreationDto){
//        List<OrderItem>  items = new ArrayList<>();
//        items.add(new OrderItem("sku_1-error", "default-port"));
//        items.add(new OrderItem("sku_2-error", "default-port"));
//        return Mono.just(new OrderCreationDto(items,"Invoice", LocalDateTime.now())).block();
//    }
}
