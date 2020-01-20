package com.investment.trading.utils;


import com.investment.trading.kafka.avro.Order;


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


}
