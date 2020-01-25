package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.account.AccountCreatedDto;
import com.investment.feign_hystrix.integration.domain.account.AccountCreationDto;
import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class IntegrationAccountController {

    private final IntegrationClient integrationClient;

    @PostMapping(value = "/account")
    public AccountCreatedDto createAccount(AccountCreationDto accountCreationDto){
        return integrationClient.createAccount(accountCreationDto);
    }

    @PatchMapping(value = "/account/{accountId}")
    public AccountCreatedDto updateAccount(String accountId, AccountCreationDto accountCreationDto){
        return integrationClient.updateAccount(accountId, accountCreationDto);
    }

    @DeleteMapping(value = "/{accountId}/status/{status}")
    public void lockAccount(String id, AccountStatus status){
        integrationClient.lockAccount(id, status);
    }

    @GetMapping(value = "/{accountId}")
    public AccountCreatedDto getAccountById(String id){
        return integrationClient.getAccountById(id);
    }

}
