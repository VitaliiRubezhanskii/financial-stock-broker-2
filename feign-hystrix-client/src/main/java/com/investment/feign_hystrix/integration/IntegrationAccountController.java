package com.investment.feign_hystrix.integration;

import com.investment.feign_hystrix.integration.domain.account.AccountCreatedDto;
import com.investment.feign_hystrix.integration.domain.account.AccountCreationDto;
import com.investment.feign_hystrix.integration.domain.enums.AccountStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequiredArgsConstructor
public class IntegrationAccountController {

//    private final FeignClientFactory feignClientFactory;

    private final IntegrationClient integrationClient;
    private final AccountServiceClient accountServiceClient;


    @PostMapping(value = "/account")
    public AccountCreatedDto createAccount(@RequestBody AccountCreationDto accountCreationDto) {
        return integrationClient.createAccount(accountCreationDto);
    }

    @PatchMapping(value = "/account/{accountId}")
    public AccountCreatedDto updateAccount(@PathVariable(value = "accountId") String accountId, @RequestBody AccountCreationDto accountCreationDto) {
        return integrationClient.updateAccount(accountId, accountCreationDto);
    }

    @DeleteMapping(value = "/{accountId}/status/{status}")
    public void lockAccount(@PathVariable(value = "accountId") String id, @PathVariable(value = "status") AccountStatus status) {
        integrationClient.lockAccount(id, status);
    }

    @GetMapping(value = "/{accountId}")
    public AccountCreatedDto getAccountById(@PathVariable(value = "accountId") String id) {
        return integrationClient.getAccountById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/account/name/{account}/sync")
    public List<AccountCreatedDto> getAccountByAccount(@PathVariable(value = "account") String account) {
        List<AccountCreatedDto> accounts = new ArrayList<>();
        long t1 = System.currentTimeMillis();

        accounts.add(accountServiceClient.getAccountByAccount(account));
        accounts.add(accountServiceClient.getAccountByAccount(account));

        long t2 = System.currentTimeMillis();

        System.out.println("Execution time = " + (t2 - t1) / 1000 + " seconds");
        return accounts;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/account/name/{account}/async")
    @Async
    public CompletableFuture<List<AccountCreatedDto>> getAccountByAccountAsync(@PathVariable(value = "account") String account) {
        List<AccountCreatedDto> accounts = new ArrayList<>();
        long t1 = System.currentTimeMillis();

        CompletableFuture<AccountCreatedDto> accountFirst = supplyAsync(() -> accountServiceClient.getAccountByAccount(account));
        CompletableFuture<AccountCreatedDto> accountSecond = supplyAsync(() -> accountServiceClient.getAccountByAccount(account));

        long t2 = System.currentTimeMillis();


        CompletableFuture<List<AccountCreatedDto>> accountDtos = accountFirst.thenCombine(accountSecond, (x, y)-> {
            accounts.add(x);
            accounts.add(y);
            return accounts;
        });
        System.out.println("Execution time = " + (t2 - t1) / 1000 + " seconds");
        return accountDtos;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/account/name/{account}/noauth")
    public AccountCreatedDto getAccountByAccountNoAuth(@PathVariable(value = "account") String account) {
        return accountServiceClient.getAccountByAccount(account);
    }

}
