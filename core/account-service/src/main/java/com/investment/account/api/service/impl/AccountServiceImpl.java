package com.investment.account.api.service.impl;

import com.investment.account.model.domain.enums.AccountStatus;
import com.investment.account.model.dto.AccountCreatedDto;
import com.investment.account.model.dto.AccountCreationDto;
import com.investment.account.mapper.AccountMapper;
import com.investment.account.api.repository.AccountRepository;
import com.investment.account.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountCreatedDto createAccount(AccountCreationDto accountCreationDto) {
       return Optional.ofNullable(accountCreationDto)
                .map(dto -> accountRepository.save(accountMapper.toEntity(accountCreationDto)))
                .map(accountMapper::toDto).orElse(new AccountCreatedDto());
    }

    @Override
    public AccountCreatedDto updateAccount(String accountId, AccountCreationDto accountCreationDto) {
        return accountRepository.findById(accountId)
                .map(account -> accountMapper.update(accountCreationDto, account))
                .map(account -> accountMapper.toDto(accountRepository.save(account))).orElse(new AccountCreatedDto());
    }

    @Override
    public void lockAccount(String accountId, AccountStatus status) {
        accountRepository.findById(accountId)
                .ifPresent(account -> account.setStatus(status));
    }

    @Override
    public AccountCreatedDto getAccountById(String id) {
        return accountRepository.findById(id)
                .map(accountMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
