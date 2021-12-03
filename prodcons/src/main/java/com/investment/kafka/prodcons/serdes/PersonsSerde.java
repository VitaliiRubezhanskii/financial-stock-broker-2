package com.investment.kafka.prodcons.serdes;//package com.investment.serde;


import com.investment.kafka.prodcons.avro.Person;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class PersonsSerde implements Serde<Person> {

    private final Serde<Person> inner;

    public PersonsSerde() {
        this.inner = Serdes.serdeFrom(new SpecificAvroSerializer<Person>(), new SpecificAvroDeserializer<Person>());
    }

    @Override
    public Serializer<Person> serializer() {
        return this.inner.serializer();
    }

    @Override
    public Deserializer<Person> deserializer() {
        return this.inner.deserializer();
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> serializerConfig, boolean isSerializerForRecordKeys) {
        final Map<String, String> serdeConfig = Collections.singletonMap(
                AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
        serializer().configure(serdeConfig, isSerializerForRecordKeys);
        deserializer().configure(serdeConfig, isSerializerForRecordKeys);
    }

}
