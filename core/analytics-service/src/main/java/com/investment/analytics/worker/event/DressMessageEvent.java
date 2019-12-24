package com.investment.analytics.worker.event;


import com.investment.analytics.DressStatus;
import com.investment.analytics.worker.domain.Dress;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DressMessageEvent extends DressEvent {

    private static final long serialVersionUID = 1126074635410771217L;

    private Dress payload;
    private DressStatus status;

    @Override
    public DressEventType getEventType() {
        return (status == DressStatus.UPDATED ? DressEventType.UPDATED : DressEventType.CREATED);
    }

}
