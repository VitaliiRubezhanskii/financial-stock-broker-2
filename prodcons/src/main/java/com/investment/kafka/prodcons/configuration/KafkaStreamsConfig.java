package com.investment.kafka.prodcons.configuration;

import com.investment.kafka.prodcons.avro.Person;
import com.investment.kafka.prodcons.serdes.PersonsSerde;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.processor.FailOnInvalidTimestamp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaStreamsConfig {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    @Primary
    public KafkaStreamsConfiguration kafkaStreamsConfiguration() {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "default");
        setDefaults(config);
        return new KafkaStreamsConfiguration(config);
    }

    public void setDefaults(Map<String, Object> config) {
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:9092");
        config.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");

        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, PersonsSerde.class.getName());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PersonsSerde.class.getName());

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, FailOnInvalidTimestamp.class);
    }


    @Bean
    public KStream<String, String> kafkaStreams(StreamsBuilder streamsBuilder) {
       return streamsBuilder.<Person, Person>stream("persons")
                .map((k, v) -> {
                    log.info("consumed from stream value {}", v);
                    return new KeyValue<String, String>(k.getFirstName(), v.getLastName());
                });
    }
}
