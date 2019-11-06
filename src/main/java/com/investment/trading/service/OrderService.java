package com.investment.trading.service;

import com.investment.trading.domain.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Order> newOrder(Order order);

    Mono<Order> findOrderById(String id);

    Flux<Order> findAllOrdersByContainingSKU(String sku);

    Mono<Void> deleteById(String id);


}
