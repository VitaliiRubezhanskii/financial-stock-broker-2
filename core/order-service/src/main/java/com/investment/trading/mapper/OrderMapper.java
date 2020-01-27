package com.investment.trading.mapper;

import com.investment.trading.model.domain.Order;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel="spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    Order toEntity(OrderCreationDto orderCreationDto);

//    Order fromCreatedToEntity(OrderRequest dto);

    OrderCreatedDto toDto(Order order);



    OrderCreationDto fromOrderCreatedDto(OrderCreatedDto orderCreatedDto);

    OrderCreationDto update(OrderCreatedDto orderCreatedDto, @MappingTarget OrderCreationDto orderCreationDto);




}
