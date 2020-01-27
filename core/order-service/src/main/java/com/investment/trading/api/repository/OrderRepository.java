package com.investment.trading.api.repository;

import com.investment.trading.model.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findOrdersByAccount(String account);

}
