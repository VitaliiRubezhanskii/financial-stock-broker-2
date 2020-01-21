package com.investment.trading.utils;


import com.investment.trading.kafka.avro.Order;
import com.investment.trading.kafka.avro.OrderResponse;
import com.investment.trading.model.dto.OrderCreationDto;


public class OrderUtils {

   public static Order payloadFromOrderEntity(com.investment.trading.model.domain.Order order){
       return Order.newBuilder()
                   .setAccount(order.getAccount())
                   .setId(order.getId())
                   .setCondition(order.getCondition())
                   .setTicket(order.getTicket())
                   .setVolume(order.getVolume())
                   .setAsk(order.getAsk())
                   .setBid(order.getBid()).build();
   }

   public static OrderCreationDto mapOrderRequestToOrderCreationDto(OrderResponse orderResponse){
       OrderCreationDto orderDto = new OrderCreationDto();
       orderDto.setAccount(orderResponse.getAccount().toString());
       orderDto.setAsk(orderResponse.getAsk().toString());
       orderDto.setBid(orderResponse.getBid().toString());
       orderDto.setTicket(orderResponse.getTicket().toString());
       orderDto.setVolume(orderResponse.getVolume().toString());
       orderDto.setId(orderResponse.getId().toString());
       return orderDto;
   }


}
