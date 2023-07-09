package com.investment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationDto {

    private String id;

    private String ticket;

    private String volume;

    private String condition;

    private String account;

    private String bid;

    private String ask;
}
