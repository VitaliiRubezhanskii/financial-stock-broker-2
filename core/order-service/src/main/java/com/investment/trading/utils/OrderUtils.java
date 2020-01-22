package com.investment.trading.utils;


import com.investment.trading.kafka.avro.Order;
import com.investment.trading.kafka.avro.OrderRequest;
import com.investment.trading.kafka.avro.OrderResponse;
import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.springframework.data.mongodb.core.messaging.Message;


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
