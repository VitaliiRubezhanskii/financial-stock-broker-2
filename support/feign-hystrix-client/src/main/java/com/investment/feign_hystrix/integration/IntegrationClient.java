package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.account.AccountCreatedDto;
import com.investment.feign_hystrix.integration.domain.account.AccountCreationDto;
import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import com.investment.feign_hystrix.integration.domain.orders.OrderCreatedDto;
import com.investment.feign_hystrix.integration.domain.orders.OrderCreationDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntegrationClient {

    private final AccountServiceClient accountServiceClient;

    private final OrderServiceClient orderServiceClient;

    @HystrixCommand(fallbackMethod = "saveOrderDataAndNotifyUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public OrderCreatedDto newOrder(OrderCreationDto orderCreationDto){
        return orderServiceClient.newOrder(orderCreationDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(fallbackMethod = "cacheRequestAndNotifyUser")
    public OrderCreatedDto findOrderById(String id){
        return orderServiceClient.findOrderById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(fallbackMethod = "cacheRequestAndNotifyUser")
    public List<OrderCreatedDto> findOrdersByAccountId(String accountID){
        return orderServiceClient.findOrdersByAccountId(accountID);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @HystrixCommand(fallbackMethod = "saveAccountDataAndNotifyUser")
    public AccountCreatedDto createAccount(AccountCreationDto accountCreationDto){
        return accountServiceClient.createAccount(accountCreationDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @HystrixCommand(fallbackMethod = "saveAccountDataAndNotifyUser")
    public AccountCreatedDto updateAccount(String accountId, AccountCreationDto accountCreationDto){
        return accountServiceClient.updateAccount(accountId, accountCreationDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @HystrixCommand(fallbackMethod = "cacheRequestAndNotifyUserThenLockAccount")
    public void lockAccount(String id, AccountStatus status){
        accountServiceClient.lockAccount(id, status);
    }

    @HystrixCommand(fallbackMethod = "cacheRequestAndNotifyUser")
    public AccountCreatedDto getAccountById(String id){
        return accountServiceClient.getAccountById(id);
    }

    public String saveAccountDataAndNotifyUser(String accountId, AccountCreationDto accountCreationDto){
        return "Account with id " + accountId + " cant be created now, we saved your data and will create account for you" +
                "with such info: " + accountCreationDto;
    }

    public String cacheRequestAndNotifyUserThenLockAccount(String id, AccountStatus status){
        return "Account with id " + id + "cant be locked now, we saved your data and will lock to status " + status;
    }

    public String saveOrderDataAndNotifyUser(OrderCreationDto orderCreationDto){
        return "Order for " + orderCreationDto.getTicket() + " cant be created now, we saved your data and will create account for you" +
                "with such info: " + orderCreationDto;
    }

    public String cacheRequestAndNotifyUser(String id){
        return "data is saved";
    }















//
//    @HystrixCommand(fallbackMethod = "getDefaultOrders")
//    public OrderCreationDto getOrders(String id){
//        return orderServiceClient.get(id);
//    }
//
//    @HystrixCommand(fallbackMethod = "getDefaultExample")
//    public OrderCreationDto getExample(){
//        return orderServiceClient.getExample();
//    }
//
//    public OrderCreatedDto create(OrderCreationDto orderCreationDto){
//        return orderServiceClient.create(orderCreationDto);
//    }
//
//    @HystrixCommand(fallbackMethod = "getDefaultHello")
//    public String getHello(){
//        return accountServiceClient.getHello();
//    }

}
