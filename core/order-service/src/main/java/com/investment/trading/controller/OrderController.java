package com.investment.trading.controller;

import com.investment.trading.domain.Order;
import com.investment.trading.domain.OrderItem;
import com.investment.trading.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @Value("${server.port}")
    private String port;




    @GetMapping("/sku/{sku}")
    public Order getAllOrdersContainingSKU(@PathVariable("sku") String sku){
        return orderService.findAllOrdersByContainingSKU(sku).blockFirst();
    }

    @GetMapping("/order/sample")
    public Order getExample(){
            List<OrderItem> items = new ArrayList<>();
            items.add(new OrderItem("sku_1-real", port));
            items.add(new OrderItem("sku_2-real", port));
            log.info("Request from the port =[" + port + "]");
            Order o = new Order(items,"Invoice", LocalDateTime.now());
            log.info(String.format("invoice=[%s] firstItem=[%s] ", o.getInvoice(), o.getItems().get(0)));
            return Mono.just(o).block();
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable("id") String id) throws Exception {
       return orderService.findOrderById(id).block();

    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.newOrder(order).block();
    }

    @PutMapping("/{id}/{operation}")
    public Order update(@PathVariable("id") String id, @PathVariable("operation") OrderUpdationOperation operation,  @RequestBody List<OrderItem> orderItems) {
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
                .flatMap(orderService::newOrder).block();
    }
//

    enum OrderUpdationOperation{
        ADD,DROP
    }

}
