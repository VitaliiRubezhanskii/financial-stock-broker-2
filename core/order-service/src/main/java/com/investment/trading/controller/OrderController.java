package com.investment.trading.controller;

import com.investment.trading.domain.Order;
import com.investment.trading.domain.OrderItem;
import com.investment.trading.dto.OrderCreationDto;
import com.investment.trading.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController(value = "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    private final Processor processor;

    @Value("${server.port}")
    private String port;

    @PostMapping
    public void create(@RequestBody com.investment.trading.avro.Order orderData) {
        processor.output()
                    .send(MessageBuilder
                            .withPayload(com.investment.trading.avro.Order.newBuilder()
                                    .setId(1)
                                    .setBatch(Integer.valueOf(port))
                                    .setTicket(orderData.getTicket())
                                    .build())
                            .setHeader(KafkaHeaders.MESSAGE_KEY, 1)
                            .build());
    }


    @GetMapping("/sku/{sku}")
    public Flux<Order> getAllOrdersContainingSKU(@PathVariable("sku") String sku){
        return orderService.findAllOrdersByContainingSKU(sku);
    }

    @GetMapping("/order/sample")
//    @PreAuthorize("hasRole('ROLE_USER')")
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
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Order get(@PathVariable("id") String id) throws Exception {
       return orderService.findOrderById(id).block();

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
