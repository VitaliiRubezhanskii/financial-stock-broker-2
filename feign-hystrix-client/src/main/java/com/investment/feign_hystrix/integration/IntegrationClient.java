package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.account.AccountCreatedDto;
import com.investment.feign_hystrix.integration.domain.account.AccountCreationDto;
import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import com.investment.feign_hystrix.integration.domain.orders.OrderCreatedDto;
import com.investment.feign_hystrix.integration.domain.orders.OrderCreationDto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IntegrationClient {

    private final AccountServiceClient accountServiceClient;

    private final OrderServiceClient orderServiceClient;

//    @HystrixCommand(fallbackMethod = "saveOrderDataAndNotifyUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public OrderCreatedDto newOrder(OrderCreationDto orderCreationDto){
        return orderServiceClient.newOrder(orderCreationDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
//    @HystrixCommand(fallbackMethod = "cacheRequestAndNotifyUser")
    public OrderCreatedDto findOrderById(String id){
        return orderServiceClient.findOrderById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
//    @HystrixCommand(fallbackMethod = "cacheRequestAndNotifyUser")
    public List<OrderCreatedDto> findOrdersByAccountId(String accountID){
        return orderServiceClient.findOrdersByAccountId(accountID);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @HystrixCommand(fallbackMethod = "saveAccountDataAndNotifyUser")
    public AccountCreatedDto createAccount(AccountCreationDto accountCreationDto){
        return accountServiceClient.createAccount(accountCreationDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @HystrixCommand(fallbackMethod = "saveAccountDataAndNotifyUser")
    public AccountCreatedDto updateAccount(String accountId, AccountCreationDto accountCreationDto){
        return accountServiceClient.updateAccount(accountId, accountCreationDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @HystrixCommand(fallbackMethod = "cacheRequestAndNotifyUserThenLockAccount")
    public void lockAccount(String id, AccountStatus status){
        accountServiceClient.lockAccount(id, status);
    }

//    @HystrixCommand(fallbackMethod = "cacheRequestAndNotifyUser")
    public AccountCreatedDto getAccountById(String id){
        return accountServiceClient.getAccountById(id);
    }

    public AccountCreatedDto saveAccountDataAndNotifyUser(String accountId, AccountCreationDto accountCreationDto){
        AccountCreatedDto accountCreatedDto = new AccountCreatedDto();
        accountCreatedDto.setAccount("Account with id " + accountId + " cant be created now, we saved your data and will create account for you" +
                "with such info: " + accountCreationDto);
        return accountCreatedDto;
    }

    public void cacheRequestAndNotifyUserThenLockAccount(String id, AccountStatus status){
        System.out.println("Account with id " + id + "cant be locked now, we saved your data and will lock to status " + status);
    }

    public OrderCreatedDto saveOrderDataAndNotifyUser(OrderCreationDto orderCreationDto){
        OrderCreatedDto orderCreatedDto = new OrderCreatedDto();
         orderCreatedDto.setAccount("Order for " + orderCreationDto.getTicket() + " cant be created now, we saved your data and will create account for you" +
                "with such info: " + orderCreationDto);
         return orderCreatedDto;
    }

    public List<OrderCreatedDto> cacheRequestAndNotifyUser(String id){
        OrderCreatedDto orderCreatedDto = new OrderCreatedDto();
        orderCreatedDto.setAccount("data is saved");
        List<OrderCreatedDto> orderCreatedDtos = new ArrayList<>();
        orderCreatedDtos.add(orderCreatedDto);
        return orderCreatedDtos;
    }
}
