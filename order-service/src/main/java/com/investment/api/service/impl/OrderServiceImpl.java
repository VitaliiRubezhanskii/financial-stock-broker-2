package com.investment.api.service.impl;

import com.investment.api.repository.OrderRepository;
import com.investment.api.service.OrderService;
import com.investment.mapper.OrderMapper;
import com.investment.model.domain.Order;
import com.investment.model.dto.OrderCreatedDto;
import com.investment.model.dto.OrderCreationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderCreatedDto newOrder(OrderCreationDto orderCreationDto) {
        return Optional.ofNullable(orderCreationDto)
                .map(dto -> {
                  Order order = orderRepository.save(orderMapper.toEntity(dto));
                  log.info("Order placrd with id  {} for ticket {} of volume {} " +
                          "with condition to {} for bid and ask prices {} {} respectively",
                          order.getId(), order.getVolume(), order.getCondition(), order.getBid(), order.getAsk());
                    return order;
                })
                .map(orderMapper::toDto).orElse(new OrderCreatedDto());


    }

    @Override
    public OrderCreatedDto findOrderById(String id) {
        return orderRepository.findById(id)
                .map(order -> {
                    log.info("Order with id {} found for ticket {}", order.getId(), order.getTicket());
                    return orderMapper.toDto(order);
                })
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public List<OrderCreatedDto> findOrdersByAccountId(String accountID) {
         return orderRepository.findOrdersByAccount(accountID)
                 .stream()
                 .map(order -> {
                     log.info("Orders with id [] found, for ticket {}",
                             order.getId(), order.getTicket());
                     return orderMapper.toDto(order);
                 })
                 .collect(Collectors.toList());
    }

}
