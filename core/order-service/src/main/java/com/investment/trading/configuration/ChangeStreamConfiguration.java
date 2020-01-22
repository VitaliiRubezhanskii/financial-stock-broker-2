package com.investment.trading.configuration;

import com.google.gson.Gson;
import com.investment.trading.kafka.avro.Order;
import com.investment.trading.kafka.avro.OrderRequest;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.*;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import static com.investment.trading.utils.OrderUtils.payloadFromOrderEntity;
import static com.investment.trading.utils.OrderUtils.payloadToOrderRequest;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Configuration
@RequiredArgsConstructor
@EnableBinding(Processor.class)
public class ChangeStreamConfiguration {

    private final Processor processor;

    private final Gson gson = new Gson();

    @Bean
    public Subscription streamOrderRequestToKafkaTopic(MessageListenerContainer container) {
        return container.register(ChangeStreamRequest
                .builder(
                        this::convert
//                        stream -> sendToKafkaTopic(
//                                payloadToOrderRequest(gson.fromJson(stream.getRaw().getFullDocument().toJson(), OrderCreatedDto.class)), processor.output())
                )
                .collection("order")
                .filter(newAggregation(match(where("operationType").is("insert"))))
                .build(), com.investment.trading.model.domain.Order.class);
    }

//    @Bean
//    public Subscription streamOrderToKafkaTopic(MessageListenerContainer container) {
//        return container.register(ChangeStreamRequest.builder(stream -> sendToKafkaTopic((Message)stream.getBody(), processor.ordersChannel()))
//                .collection("order")
//                .filter(newAggregation(match(where("operationType").is("update"))))
//                .build(), com.investment.trading.kafka.avro.Order.class);
//    }

    @Bean
    public MessageListenerContainer messageListenerContainer(MongoTemplate template) {
        return new DefaultMessageListenerContainer(template) {
            @Override
            public boolean isAutoStartup() {
                return true;
            }
        };
    }

    private void sendToKafkaTopic(OrderRequest message, MessageChannel channel){
       channel.send(MessageBuilder
                        .withPayload(message)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, 1)
                        .build());

    }

    private void convert(Message<ChangeStreamDocument<Document>, com.investment.trading.model.domain.Order> message){
        System.out.println("Received message with id: " + message.getRaw() + "as json: --> " + gson.fromJson(message.getRaw().getFullDocument().toJson(), OrderCreatedDto.class));
    }



}
