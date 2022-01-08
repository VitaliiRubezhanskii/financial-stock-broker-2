package com.investment.kafka.prodcons.serdes;//package com.investment.serde;

import com.investment.kafka.prodcons.avro.Person;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.util.SerializationUtils;

import java.util.Map;

public class PersonsAvroDeserializer implements Deserializer<Person> {

    private final KafkaAvroDeserializer inner;

    public PersonsAvroDeserializer() {
        this.inner = new KafkaAvroDeserializer();
    }

    public void configure(Map<String, ?> deserializerConfig, boolean isDeserializerForRecordKeys) {
        this.inner.configure(deserializerConfig, isDeserializerForRecordKeys);
    }

    public Person deserialize(String topic, byte[] bytes) {
        System.out.println("Deserialized = " + new String(bytes));
        return (Person) SerializationUtils.deserialize(bytes);
    }

    public void close() {
        this.inner.close();
    }
}
