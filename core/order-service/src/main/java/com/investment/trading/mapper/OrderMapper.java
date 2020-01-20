package com.investment.trading.mapper;

import com.investment.trading.avro.OrderResponse;
import com.investment.trading.domain.Order;
import com.investment.trading.dto.OrderCreatedDto;
import com.investment.trading.dto.OrderCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel="spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    Order toEntity(OrderCreationDto orderCreationDto);

    OrderCreatedDto toDto(Order order);

}
