package com.investment.kafka.prodcons.serdes;//package com.investment.serde;

import com.investment.kafka.prodcons.avro.Person;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.util.SerializationUtils;

import java.util.Map;

public class PersonsAvroSerializer implements Serializer<Person> {

    @Override
    public byte[] serialize(String topic, Person data) {
        return SerializationUtils.serialize(data);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Person data) {
        return SerializationUtils.serialize(data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
