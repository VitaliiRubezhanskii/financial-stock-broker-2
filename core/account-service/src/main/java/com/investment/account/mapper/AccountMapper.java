package com.investment.account.mapper;

import com.investment.account.model.domain.Account;
import com.investment.account.model.dto.AccountCreatedDto;
import com.investment.account.model.dto.AccountCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel="spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    Account toEntity(AccountCreationDto accountCreationDto);

    AccountCreatedDto toDto(Account account);

    Account update(AccountCreationDto accountCreationDto, @MappingTarget Account account);
}
