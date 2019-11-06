package com.investment.trading.repository;

import com.investment.trading.domain.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

    Flux<Order> findOrderByItemsContaining(String sku);

}
