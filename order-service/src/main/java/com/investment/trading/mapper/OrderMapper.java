package com.investment.trading.mapper;

import com.investment.trading.model.domain.Order;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface OrderMapper {

    Order toEntity(OrderCreationDto orderCreationDto);

    OrderCreatedDto toDto(Order order);
}
