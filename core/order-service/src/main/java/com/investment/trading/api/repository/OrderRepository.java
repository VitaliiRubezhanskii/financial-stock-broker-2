package com.investment.trading.repository;

import com.investment.trading.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends MongoRepository<Order, String> {

    Order findOrderByItemsContaining(String sku);

}
