package com.investment.analytics.worker.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBinding(Source.class)
@EnableScheduling
@Profile({"development", "docker", "test"})
@Slf4j
@AllArgsConstructor
public class DressEventOutputStream {

    private final Source source;

    private final SpamGenerator spamGenerator;

    @Scheduled(fixedRate = 3000)
    private void produce(){
        DressMessageEvent dressMessageEvent = spamGenerator.messageEvent();
        System.out.println(dressMessageEvent);
        source.output().send(MessageBuilder.withPayload(dressMessageEvent).build());
    }

}
