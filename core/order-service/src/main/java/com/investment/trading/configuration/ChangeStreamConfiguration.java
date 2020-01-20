package com.investment.trading.configuration;

import com.investment.trading.domain.Order;
import com.investment.trading.utils.OrderUtils;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.*;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;


import static com.investment.trading.utils.OrderUtils.payloadFromOrderEntity;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Configuration
@RequiredArgsConstructor
public class ChangeStreamConfiguration {

    private final Processor processor;

    @Bean
    public Subscription emitOrderRequestToKafkaTopic(MessageListenerContainer container) {
        return container.register(ChangeStreamRequest.builder(this::sendToKafkaTopic)
                .collection("order")
                .filter(newAggregation(match(where("operationType").is("update"))))
                .build(), Order.class);
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

    private void sendToKafkaTopic(Message<ChangeStreamDocument<Document>, Order> message){
        processor.output()
                .send(MessageBuilder
                        .withPayload(payloadFromOrderEntity(message.getBody()))
                        .setHeader(KafkaHeaders.MESSAGE_KEY, 1)
                        .build());

    }

}
