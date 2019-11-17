package com.investment.trading.controller;

import com.google.gson.Gson;
import com.investment.trading.domain.Order;
import com.investment.trading.domain.OrderItem;
import com.investment.trading.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final Gson gson = new Gson();

    @GetMapping("/sku/{sku}")
    public Flux<Order> getAllOrdersContainingSKU(@PathVariable("sku") String sku){
        return orderService.findAllOrdersByContainingSKU(sku);
    }

    @PatchMapping("/sample/order")
    public Mono<Order> getExample(){
        return Mono.just(new Order("F4152", List.of(new OrderItem("sku_1"), new OrderItem("sku_2")), new File("Invoice"), LocalDateTime.now()));
    }

    @GetMapping("/{id}")
    public Mono<Order> get(@PathVariable("id") String id) throws Exception {
       return orderService.findOrderById(id);

    }

    @PostMapping
    public Mono<Order> create(@RequestBody Order order) {

        return orderService.newOrder(order);
    }
//
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return orderService.deleteById(id);
    }

    @PutMapping("/{id}/{operation}")
    public Mono<Order> update(@PathVariable("id") String id, @PathVariable("operation") OrderUpdationOperation operation,  @RequestBody List<OrderItem> orderItems) {
        return orderService.findOrderById(id)
                .map(p -> {
                    if (p.getItems() != null && !p.getItems().isEmpty() && operation == OrderUpdationOperation.ADD){
                        List<OrderItem> orderItemsList = p.getItems();
                        orderItemsList.addAll(orderItems);
                        p.setItems(orderItemsList);
                    }
                    if (p.getItems() != null && !p.getItems().isEmpty() && operation == OrderUpdationOperation.DROP){
                        List<OrderItem> orderItemsList = p.getItems();
                        orderItemsList.retainAll(orderItems);
                        p.setItems(orderItemsList);
                    }
                    if (p.getItems() == null &&  operation == OrderUpdationOperation.ADD){
                        p.setItems(orderItems);
                    }
                    return p;
                })
                .flatMap(orderService::newOrder);
    }
//

    enum OrderUpdationOperation{
        ADD,DROP
    }

}
