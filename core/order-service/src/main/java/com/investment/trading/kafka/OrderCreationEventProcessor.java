package com.investment.trading.kafka;

import avro.Order;
import avro.OrderRequest;
import avro.OrderResponse;
import com.investment.trading.api.service.OrderService;
import com.investment.trading.kafka.processors.KafkaProcessor;
import com.investment.trading.mapper.OrderMapper;
import com.investment.trading.utils.OrderUtils;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
@EnableBinding(KafkaProcessor.class)
@Slf4j
public class OrderCreationEventProcessor {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    private final KafkaProcessor processor;


    @StreamListener("input0")
    @SendTo("output1")
    public KStream<String, Order> process(KStream<String, OrderResponse> input) {

        final Map<String, String> serdeConfig = Collections.singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
//        final SpecificAvroSerde<OrderRequest> orderRequestSpecificAvroSerde = new SpecificAvroSerde<>();
        final SpecificAvroSerde<OrderResponse> orderResponseSpecificAvroSerde = new SpecificAvroSerde<>();
//        orderRequestSpecificAvroSerde.configure(serdeConfig, false);
        orderResponseSpecificAvroSerde.configure(serdeConfig, false);

        return input
                .filter((key, value) -> true)
                .map((k, value) ->  new KeyValue<>(k, Order.newBuilder().setId(3).build()));

    }



//    @StreamListener(KafkaProcessor.INPUT)
//    public void consumeOrderResponse(OrderResponse orderResponse) {
//        final Map<String, String> serdeConfig = Collections.singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
////        final SpecificAvroSerde<OrderRequest> orderRequestSpecificAvroSerde = new SpecificAvroSerde<>();
////        final SpecificAvroSerde<com.investment.trading.kafka.avro.Order> orderSpecificAvroSerde = new SpecificAvroSerde<>();
//        final SpecificAvroSerde<OrderResponse> orderResponseSpecificAvroSerde = new SpecificAvroSerde<>();
////        orderRequestSpecificAvroSerde.configure(serdeConfig, false);
//        orderResponseSpecificAvroSerde.configure(serdeConfig, false);
////        orderSpecificAvroSerde.configure(serdeConfig, false);
//
//        Order order = OrderUtils.mapOrderRequestToOrderCreationDto(orderResponse);
//        processor.ordersChannel()
//                        .send(MessageBuilder.withPayload(Order.newBuilder().setId(3).build()).build());
////        orderService.newOrder(orderDto)
////                .map(message-> processor.ordersChannel()
////                        .send(MessageBuilder.withPayload(payloadFromOrderEntity(message)).build()));
//
//    }
}
