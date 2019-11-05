package com.investment.trading.repository;

import com.investment.trading.domain.OrderItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderItemRepository extends ReactiveMongoRepository<OrderItem, String> {
}
