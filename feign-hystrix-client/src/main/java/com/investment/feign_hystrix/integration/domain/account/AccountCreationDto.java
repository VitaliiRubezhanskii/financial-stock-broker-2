package com.investment.feign_hystrix.integration.domain.account;

import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import lombok.*;

import java.math.BigDecimal;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AccountCreationDto {

    private String userId;

    private String address;

    private BigDecimal balance;

    private AccountStatus status;

}
