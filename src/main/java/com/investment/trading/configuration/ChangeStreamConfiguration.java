package com.investment.trading.configuration;

import com.investment.trading.domain.Order;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Configuration
@RequiredArgsConstructor
public class ChangeStreamConfiguration {

    @Bean
    public Subscription changeStreamIterable(MessageListenerContainer container) {
        ChangeStreamRequest<Order> request = ChangeStreamRequest.builder(new MessageListener<ChangeStreamDocument<Document>, Order>() {
            @Override
            public void onMessage(Message<ChangeStreamDocument<Document>, Order> message) {
                System.out.println("Received message with id: " + message.getRaw() + " ---------- " + message.getBody());
            }
        })
                .collection("order")
                .filter(newAggregation(match(where("operationType").is("insert"))))
                .build();

        return container.register(request, Order.class);
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
}
