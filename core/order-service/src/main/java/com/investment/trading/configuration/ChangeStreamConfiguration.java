package com.investment.trading.configuration;

import com.investment.trading.kafka.avro.OrderRequest;
import com.investment.trading.kafka.processors.KafkaProcessor;
import com.investment.trading.model.domain.Order;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.*;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;


import static com.investment.trading.utils.OrderUtils.payloadFromOrderEntity;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Configuration
@RequiredArgsConstructor
public class ChangeStreamConfiguration {

    private final KafkaProcessor processor;

    @Bean
    public Subscription streamOrderRequestToKafkaTopic(MessageListenerContainer container) {
        return container.register(ChangeStreamRequest.builder(stream -> sendToKafkaTopic((Message)stream.getBody(), processor.orderRequestChannel()))
                .collection("order")
                .filter(newAggregation(match(where("operationType").is("insert"))))
                .build(), OrderRequest.class);
    }

    @Bean
    public Subscription streamOrderToKafkaTopic(MessageListenerContainer container) {
        return container.register(ChangeStreamRequest.builder(stream -> sendToKafkaTopic((Message)stream.getBody(), processor.ordersChannel()))
                .collection("order")
                .filter(newAggregation(match(where("operationType").is("update"))))
                .build(), com.investment.trading.kafka.avro.Order.class);
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(MongoTemplate template) {
        return new DefaultMessageListenerContainer(template) {
            @Override
            public boolean isAutoStartup() {
                return true;
            }
        };
    }

    private void sendToKafkaTopic(Message<ChangeStreamDocument<Document>, Order> message, MessageChannel channel){
       channel.send(MessageBuilder
                        .withPayload(payloadFromOrderEntity(message.getBody()))
                        .setHeader(KafkaHeaders.MESSAGE_KEY, 1)
                        .build());

    }

}
