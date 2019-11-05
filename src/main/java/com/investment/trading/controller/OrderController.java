package com.investment.trading.controller;

import com.investment.trading.domain.Order;
import com.investment.trading.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

//    @GetMapping
//    public Flux<Order> getAllOrders(){
//        return orderRepository.findAll();
//    }
//
    @GetMapping("/{id}")
    public Mono<Order> get(@PathVariable("id") String id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public Mono<Order> create(@RequestBody Order order) {
        return orderService.newOrder(order);
    }
//
//    @DeleteMapping("/{id}")
//    public Mono<Void> delete(@PathVariable("id") String id) {
//        return orderRepository.deleteById(id);
//    }
//
//    @PutMapping("/{id}/{operation}")
//    public Mono<Order> update(@PathVariable("id") String id, @PathVariable("operation") OrderUpdationOperation operation,  @RequestBody List<OrderItem> orderItems) {
//        return orderRepository.findById(id)
//                .map(p -> {
//                    if (p.getItems() != null && !p.getItems().isEmpty() && operation == OrderUpdationOperation.ADD){
//                        List<OrderItem> orderItemsList = p.getItems();
//                        orderItemsList.addAll(orderItems);
//                        p.setItems(orderItemsList);
//                    }
//                    if (p.getItems() != null && !p.getItems().isEmpty() && operation == OrderUpdationOperation.DROP){
//                        List<OrderItem> orderItemsList = p.getItems();
//                        orderItemsList.retainAll(orderItems);
//                        p.setItems(orderItemsList);
//                    }
//                    if (p.getItems() == null &&  operation == OrderUpdationOperation.ADD){
//                        p.setItems(orderItems);
//                    }
//                    return p;
//                })
//                .flatMap(orderRepository::save);
//    }
//

//    enum OrderUpdationOperation{
//        ADD,DROP
//    }

}
