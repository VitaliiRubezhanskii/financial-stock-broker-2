package com.investment.analytics.worker.event;

import com.investment.analytics.DressStatus;
import com.investment.analytics.worker.domain.Dress;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class SpamGenerator {

    public DressMessageEvent messageEvent(){
            DressMessageEvent dressMessageEvent = new DressMessageEvent();
            dressMessageEvent.setStatus(DressStatus.CREATED);
            Dress dress = new Dress();
            dress.setColor("RED");
            dress.setName("Message");
            dress.setId(UUID.randomUUID().toString());
            dressMessageEvent.setPayload(dress);
            return dressMessageEvent;
        }

}
