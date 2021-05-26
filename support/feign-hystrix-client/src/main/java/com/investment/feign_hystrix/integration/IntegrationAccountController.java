package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.account.AccountCreatedDto;
import com.investment.feign_hystrix.integration.domain.account.AccountCreationDto;
import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IntegrationAccountController {

    private final IntegrationClient integrationClient;
    private final AccountServiceClient accountServiceClient;


    @PostMapping(value = "/account")
    public AccountCreatedDto createAccount(@RequestBody AccountCreationDto accountCreationDto){
        return integrationClient.createAccount(accountCreationDto);
    }

    @PatchMapping(value = "/account/{accountId}")
    public AccountCreatedDto updateAccount(@PathVariable(value = "accountId") String accountId, @RequestBody AccountCreationDto accountCreationDto){
        return integrationClient.updateAccount(accountId, accountCreationDto);
    }

    @DeleteMapping(value = "/{accountId}/status/{status}")
    public void lockAccount(@PathVariable(value = "accountId") String id, @PathVariable(value = "status") AccountStatus status){
        integrationClient.lockAccount(id, status);
    }

    @GetMapping(value = "/{accountId}")
    public AccountCreatedDto getAccountById(@PathVariable(value = "accountId") String id){
        return integrationClient.getAccountById(id);
    }

    @GetMapping(value = "/account/name/{account}")
    public AccountCreatedDto getAccountByAccount(@PathVariable(value = "account") String account){
        return accountServiceClient.getAccountByAccount(account);
    }

}
