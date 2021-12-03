package com.investment.kafka.prodcons.producer;

import com.investment.kafka.prodcons.avro.Address;
import com.investment.kafka.prodcons.avro.Person;
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PersonProducer {

    private final KafkaTemplate<Person, Person> kafka;

    @Value("${de.codecentric.sbkaavro.topic}")
    private String topic;
    @Value("${de.codecentric.sbkaavro.records}")
    private Integer numRecords = 1;

    @GetMapping("/get")
    public void send() {
        Person person = Person.newBuilder()
                .setFirstName("Vitalii")
                .setLastName("Rubezhanskii")
                .build();
        Address address = Address.newBuilder()
                .setZip("61000")
                .setCity("Kharkiv")
                .setStreet("Hv Shyronintsev")
                .setStreetNumber("67")
                .build();

        log.info("producing {}, {}", person, person);
        kafka.send(topic, person, person);
    }
}
