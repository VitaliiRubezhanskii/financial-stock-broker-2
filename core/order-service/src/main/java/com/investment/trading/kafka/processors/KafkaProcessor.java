package com.investment.trading.kafka.processors;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor  {

    String INPUT = "input";

    String OUTPUT = "output0";

    String OUTPUT1 = "output1";

    @Output("output0")
    MessageChannel orderRequestChannel();

    @Output("output1")
    MessageChannel ordersChannel();

    @Input("input")
    SubscribableChannel input();

}
