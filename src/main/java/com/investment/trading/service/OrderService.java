package com.investment.trading.service;

import com.investment.trading.domain.Order;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Order> newOrder(Order order);

    Mono<Order> getOrder(String id);


}
