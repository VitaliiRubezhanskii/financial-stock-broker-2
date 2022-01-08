package com.investment.kafka.prodcons.consumer;

import com.investment.kafka.prodcons.avro.Address;
import com.investment.kafka.prodcons.avro.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonConsumer {

    private final KafkaListenerEndpointRegistry registry;

    @Value("${de.codecentric.sbkaavro.records}")
    private Integer numRecords = 1;

//
//    @KafkaListener(topics = {"${de.codecentric.sbkaavro.topic}"})
//    public void receive(ConsumerRecord<Person, Address> consumerRecord) {
//        Person person = consumerRecord.key();
//        Address address = consumerRecord.value();
//        numRecords--;
//        log.info("consuming {}, {}, remaining {}", person, address, numRecords);
//        if (numRecords <= 0) {
//            registry.stop();
//        }
//    }
}
