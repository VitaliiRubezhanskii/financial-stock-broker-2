package com.investment.trading.utils;


import avro.Order;
import avro.OrderRequest;
import avro.OrderResponse;
import com.investment.trading.model.dto.OrderCreatedDto;


public class OrderUtils {

   public static Order payloadFromOrderEntity(OrderCreatedDto order){
       return Order.newBuilder()
                   .setAccount(order.getAccount())
                   .setId(1)
                   .setCondition(order.getCondition())
                   .setTicket("Change Stream")
                   .setVolume(order.getVolume())
                   .setAsk(order.getAsk())
                   .setBid(order.getBid()).build();
   }

    public static OrderRequest payloadToOrderRequest(OrderCreatedDto message){
        return OrderRequest.newBuilder()
                .setAccount(message.getAccount())
                .setId(1)
                .setCondition("blank")
                .setTicket("blank")
                .setVolume("blank")
                .setAsk(message.getAsk())
                .setBid(message.getBid()).build();
    }

   public static Order mapOrderRequestToOrderCreationDto(OrderResponse orderResponse){
       Order orderDto = new Order();
       orderDto.setAccount(orderResponse.getAccount().toString());
       orderDto.setAsk(orderResponse.getAsk().toString());
       orderDto.setBid(orderResponse.getBid().toString());
       orderDto.setTicket(orderResponse.getTicket().toString());
       orderDto.setVolume(orderResponse.getVolume().toString());
       orderDto.setId(orderResponse.getId());
       return orderDto;
   }


}
