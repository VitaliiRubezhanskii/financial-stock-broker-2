package com.investment.api.service.impl;

import com.investment.model.domain.Account;
import com.investment.model.domain.enums.AccountStatus;
import com.investment.model.dto.AccountCreatedDto;
import com.investment.model.dto.AccountCreationDto;
import com.investment.mapper.AccountMapper;
import com.investment.api.repository.AccountRepository;
import com.investment.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountCreatedDto createAccount(AccountCreationDto accountCreationDto) {
       return Optional.ofNullable(accountCreationDto)
                .map(dto -> {
                    Account account = accountRepository.save(accountMapper.toEntity(accountCreationDto));
                    log.info("Account with id {} created", account.getId());
                    return account;
                })
                .map(accountMapper::toDto).orElse(new AccountCreatedDto());
    }

    @Override
    public AccountCreatedDto updateAccount(String accountId, AccountCreationDto accountCreationDto) {
        return accountRepository.findById(accountId)
                .map(account -> {
                    log.info("Account with id {} updated", account.getId());
                    return accountMapper.update(accountCreationDto, account);
                })
                .map(account -> accountMapper.toDto(accountRepository.save(account))).orElse(new AccountCreatedDto());
    }

    @Override
    public void lockAccount(String accountId, AccountStatus status) {
        accountRepository.findById(accountId)
                .ifPresent(account -> {
                    log.info("Account with id {} locked - status set to {}", account.getId(), status);
                    account.setStatus(status);
                });
    }

    @Override
    public AccountCreatedDto getAccountById(String id) {
        return accountRepository.findById(id)
                .map(account ->{
                    log.info("Account with id {} found", account.getId());
                    return accountMapper.toDto(account);
                })
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
