package com.investment.mapper;

import com.investment.model.domain.Order;
import com.investment.model.dto.OrderCreatedDto;
import com.investment.model.dto.OrderCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface OrderMapper {

    Order toEntity(OrderCreationDto orderCreationDto);

    OrderCreatedDto toDto(Order order);
}
