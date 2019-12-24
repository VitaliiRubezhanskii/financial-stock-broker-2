package com.investment.analytics.worker.event;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
@NoArgsConstructor(force = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DressEvent implements Serializable {

    private static final long serialVersionUID = 1126074635410771217L;

    @JsonProperty("payload_key")
    private String payloadKey;

    @JsonIgnore
    private DressEventType eventType;

    private Long timestamp;


    public DressEvent(DressEventType eventType) {
        this.eventType = eventType;
    }
}
