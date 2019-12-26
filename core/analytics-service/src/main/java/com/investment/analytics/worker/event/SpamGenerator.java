package com.investment.analytics.worker.event;

import com.investment.analytics.DressStatus;
import com.investment.analytics.worker.domain.Dress;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpamGenerator {
    public DressMessageEvent messageEvent(Long id){
            DressMessageEvent dressMessageEvent = new DressMessageEvent();
            dressMessageEvent.setStatus(DressStatus.CREATED);
            Dress dress = new Dress();
            dress.setColor("RED");
            dress.setName("Message");
            dress.setId(id.toString());
            dressMessageEvent.setPayload(dress);
            return dressMessageEvent;
        }

}
