package com.investment.feign_hystrix.integration.domain.account;

import com.investment.feign_hystrix.integration.domain.account.enums.AccountStatus;
import lombok.*;

import java.math.BigDecimal;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreatedDto {

    private String id;

    private String account;

    private BigDecimal balance;

    private AccountStatus status;

}
