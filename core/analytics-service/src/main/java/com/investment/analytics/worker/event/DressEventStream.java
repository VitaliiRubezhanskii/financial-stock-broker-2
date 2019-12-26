package com.investment.analytics.worker.event;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kstream.annotations.KStreamProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(KStreamProcessor.class)
@Profile({"development", "docker", "test"})
@Slf4j
public class DressEventStream {

    private static final String LOG_RECEIVED = "Received: ";

    @StreamListener("input")
    public void receiveDressMessageEvent(KStream<?, DressMessageEvent> incomingStream) {
        incomingStream.map((k, v) ->  KeyValue.pair(k, v.getPayload().getColor().toLowerCase())).foreach((k, v) -> log.info("key = {} | value = {} ", k, v));
    }

//    @StreamListener(target = DressInboundChannels.INBOUND_DRESSES)
//    public void receiveDressMessageEvent(DressMessageEvent dressMessageEvent) {
//        KStreamBuilder builder = new KStreamBuilder();
//        builder.stream(dressMessageEvent.getPayload().getColor()).map(KeyValue::pair).foreach((k, v) -> log.info("key = {} | value = {} ", k, v));
//        log.info(LOG_RECEIVED + " event {} ", dressMessageEvent.toString());
//
//    }

    @StreamListener(target = DressInboundChannels.INBOUND_RATINGS)
    public void receiveRatingMessageEvent(RatingMessageEvent ratingMessageEvent) {
        log.info(LOG_RECEIVED + ratingMessageEvent.toString());

    }

}



