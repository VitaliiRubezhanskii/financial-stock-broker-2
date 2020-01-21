package com.investment.account.util;

import com.investment.account.api.service.AccountService;
import com.investment.account.kafka.avro.OrderRequest;
import com.investment.account.kafka.avro.OrderResponse;
import com.investment.account.model.domain.enums.AccountStatus;
import com.investment.account.model.dto.AccountCreatedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class AccountUtils {

    private final AccountService accountService;

    public boolean checkAccount(OrderRequest orderRequest){
//        AccountCreatedDto account = accountService.getAccountById(orderRequest.getAccount().toString());
//       return account.getBalance().compareTo(new BigDecimal(orderRequest.getBid().toString())) >= 0 && account.getStatus() == AccountStatus.ACTIVE;
        return true;
    }

    public OrderResponse mapRequestToResponse(OrderRequest orderRequest){
        OrderResponse response = new OrderResponse();
        response.setAccount(orderRequest.getAccount());
        response.setAsk(orderRequest.getAsk());
        response.setBid(orderRequest.getBid());
        response.setId(orderRequest.getId());
        response.setTicket(orderRequest.getTicket());
        response.setVolume(orderRequest.getVolume());
        return response;
    }
}
