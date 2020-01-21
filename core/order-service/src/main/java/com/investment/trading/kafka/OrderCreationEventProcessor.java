package com.investment.trading.kafka;

import com.investment.trading.api.service.OrderService;
import com.investment.trading.kafka.avro.OrderRequest;
import com.investment.trading.kafka.avro.OrderResponse;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.model.dto.OrderCreationDto;
import com.investment.trading.utils.OrderUtils;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
@EnableBinding(Processor.class)
@Slf4j
public class OrderCreationEventProcessor {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @StreamListener(Processor.INPUT)
    public void consumeOrderResponse(OrderResponse orderResponse) {
        final Map<String, String> serdeConfig = Collections.singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
        final SpecificAvroSerde<OrderRequest> orderRequestSpecificAvroSerde = new SpecificAvroSerde<>();
        final SpecificAvroSerde<com.investment.trading.kafka.avro.Order> orderSpecificAvroSerde = new SpecificAvroSerde<>();
        final SpecificAvroSerde<OrderResponse> orderResponseSpecificAvroSerde = new SpecificAvroSerde<>();
        orderRequestSpecificAvroSerde.configure(serdeConfig, false);
        orderResponseSpecificAvroSerde.configure(serdeConfig, false);
        orderSpecificAvroSerde.configure(serdeConfig, false);

        OrderCreationDto orderDto = OrderUtils.mapOrderRequestToOrderCreationDto(orderResponse);
       OrderCreationDto updatedDto = orderMapper.update(orderService.findOrderById(orderResponse.getId().toString()), orderDto);
        orderService.newOrder(updatedDto);
        // save to mongo ---> emits change stream which sends data to orders topic of Kafka
    }
}
