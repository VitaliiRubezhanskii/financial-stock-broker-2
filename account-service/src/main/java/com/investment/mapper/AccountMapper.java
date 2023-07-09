package com.investment.mapper;

import com.investment.model.domain.Account;
import com.investment.model.dto.AccountCreatedDto;
import com.investment.model.dto.AccountCreationDto;
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
