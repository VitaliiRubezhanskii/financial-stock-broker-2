package com.investment.trading.utils;


import com.investment.trading.avro.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


public class OrderUtils {

   public static Order payloadFromOrderEntity(com.investment.trading.domain.Order order){
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
