package com.investment.analytics.worker.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.investment.analytics.DressStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(force = true)
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dress implements Serializable {

    private static final long serialVersionUID = 1126074635410771215L;

    private String id, name, color, season;

    private List<Image> images = new ArrayList<>();

    private Brand brand;

    @JsonIgnore
    private DressStatus status;

    private BigDecimal price;
}
