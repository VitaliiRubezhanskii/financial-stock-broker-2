package com.investment.trading.kafka;

import com.investment.trading.api.repository.OrderRepository;
import com.investment.trading.api.service.OrderService;
import com.investment.trading.kafka.avro.OrderResponse;
import com.investment.trading.kafka.processors.KafkaProcessor;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.model.domain.Order;
import com.investment.trading.model.dto.OrderCreationDto;
import com.investment.trading.utils.OrderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableBinding(KafkaProcessor.class)
@Slf4j
public class OrderCreationEventProcessor {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @StreamListener(KafkaProcessor.INPUT)
    public void consumeOrderResponse(OrderResponse orderResponse) {
        OrderCreationDto orderDto = OrderUtils.mapOrderRequestToOrderCreationDto(orderResponse);
       OrderCreationDto updatedDto = orderMapper.update(orderService.findOrderById(orderResponse.getId().toString()), orderDto);
        orderService.newOrder(updatedDto);
        // save to mongo ---> emits change stream which sends data to orders topic of Kafka
    }
}
