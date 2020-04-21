package com.investment.feign_hystrix.integration.domain.orders;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationDto {

    private String id;

    private String ticket;

    private String volume;

    private String condition;

    private String account;

    private String bid;

    private String ask;


}
