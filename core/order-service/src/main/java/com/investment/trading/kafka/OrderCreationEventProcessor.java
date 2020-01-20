package com.investment.trading.kafka;

import com.investment.trading.kafka.avro.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Processor.class)
@Slf4j
public class OrderCreationEventProcessor {

    @StreamListener(Processor.INPUT)
    public void consumeOrderResponse(Order order) {
        log.info("Let's process employee details: {}", order);
        // save to mongo ---> emits change stream which sends data to orders topic of Kafka
    }
}
