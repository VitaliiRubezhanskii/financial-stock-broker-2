package com.investment;

import com.example.Sensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@RequiredArgsConstructor
@Slf4j
public class AnalyticsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsServiceApplication.class, args);
    }

//    @Bean
//    @Primary
//    public SchemaRegistryClient schemaRegistryClient() {
//        DefaultSchemaRegistryClient client = new DefaultSchemaRegistryClient(new RestTemplate());
//        return client;
//    }

//    @Primary
//    @Order(Ordered.LOWEST_PRECEDENCE)
//    @Bean
//    public SchemaRegistryClient schemaRegistryClient() {
//        Map<String, String> configs = new HashMap<>();
//        configs.put("schema.registry.basic.auth.user.info","LQXVBK453LMWAJFK:Flj/AhQHLcHs0WpEbmCqzvbXfYhLR5ABxo8NXx/igZwwbufY0eLmzbEkxR3mfxab");
//        configs.put("basic.auth.credentials.source", "USER_INFO");
//        RestService restService = new RestService("https://psrc-2312y.europe-west3.gcp.confluent.cloud");
//        return new CachedSchemaRegistryClient(restService, 1000, configs);
//    }

    @Bean
    public Function<KStream<String, Sensor>, KStream<String, Sensor>> process() {
        return input -> {
            KStream<String, Sensor> transformedStream = input
//                    .peek((key, value) -> System.out.println("KEY = " + key + " value = " + value))
                    .mapValues((key, value) -> {
                        int total = value.getAcceleration() + value.getTemperature() + value.getVelocity();
                        return new Sensor(value.getId(), total, total, total);
                    })
                    .peek(((key, value) -> System.out.println(" Key = " + key + " Val = " + value)));
//                    .transform(()-> new Transformer<String, Sensor, KeyValue<String, Sensor>>(){
//                        @Override
//                        public void init(ProcessorContext context) { }
//
//                        @Override
//                        public KeyValue<String, Sensor> transform(String key, Sensor value) {
//                            List<Integer> quotes = new ArrayList<>();
//                            quotes.add(value.getTemperature());
//                            quotes.add(value.getVelocity());
//                            quotes.add(value.getAcceleration());
//                            Integer total = quotes.get(0) + quotes.get(1) + quotes.get(2);
//                            return new KeyValue<>(key, new Sensor("test", 2, 2, 2));
//                        }
//
//                        @Override
//                        public void close() { }
//                    });
//            transformedStream.print(Printed.toSysOut());
            return transformedStream;
        };
    }

//    private KTable<String, Integer> aggregate(KStream<String, Quote> stream) {
//        return stream
//                .map((key, value) -> new KeyValue<>(value.getTicket().toString(), Integer.parseInt(value.getHigh().toString())))
//                .groupBy((key, value) -> key, Grouped.with(Serdes.String(), Serdes.Integer()))
//                .aggregate(() -> 0, (key, value, aggregate) -> aggregate + value,
//                        Materialized.with(Serdes.String(), Serdes.Integer()));
//    }

//    private KStream<String, Integer> transform(KStream<String, Quote> stream) {
//        return stream.transform(() -> new Transformer<String, Quote, KeyValue<String, Integer>>() {
//            @Override
//            public void init(ProcessorContext context) {
//            }
//
//            @Override
//            public KeyValue<String, Integer> transform(String key, Quote value) {
//                List<Integer> quotes = new ArrayList<>();
//                quotes.add(Integer.parseInt(value.getHigh().toString()));
//                quotes.add(Integer.parseInt(value.getOpen().toString()));
//                quotes.add(Integer.parseInt(value.getLow().toString()));
//                return new KeyValue<>(key, quotes.get(0) + quotes.get(1) + quotes.get(2));
//            }
//
//            @Override
//            public void close() {
//            }
//        });
//    }

//    private void print(KStream<String, Integer> kStream) {
//        kStream.print(Printed.toSysOut());
//    }
}
