package com.investment.analytics.worker.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;

@EnableBinding(DressInboundChannels.class)
@Profile({"development", "docker", "test"})
@Slf4j
public class DressEventStream {

//    @Autowired
//    private DressEventStoreService dressEventStoreService;

//    @Autowired
//    private RatingEventStoreService ratingEventStoreService;

    private static final String LOG_RECEIVED = "Received: ";

    @StreamListener(target = DressInboundChannels.INBOUND_DRESSES)
    public void receiveDressMessageEvent(DressMessageEvent dressMessageEvent) {
        log.info(LOG_RECEIVED + dressMessageEvent.toString());
//        dressEventStoreService.apply(dressMessageEvent);
    }

    @StreamListener(target = DressInboundChannels.INBOUND_RATINGS)
    public void receiveRatingMessageEvent(RatingMessageEvent ratingMessageEvent) {
        log.info(LOG_RECEIVED + ratingMessageEvent.toString());
//        ratingEventStoreService.apply(ratingMessageEvent);
    }

}
