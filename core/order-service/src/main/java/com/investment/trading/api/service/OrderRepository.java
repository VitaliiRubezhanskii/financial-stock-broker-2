package com.investment.trading.api.service;

import com.investment.trading.model.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findOrdersByAccount(String account);

    Optional<Order> findOrderBy_id(String id);

}
