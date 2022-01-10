package com.investment.trading.api;

import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import com.investment.trading.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreatedDto> create(@RequestBody OrderCreationDto orderCreationDto) {
       return new ResponseEntity<>(orderService.newOrder(orderCreationDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderCreatedDto> findOrderById(@PathVariable("id") String id){
        OrderCreatedDto orderCreatedDto = new OrderCreatedDto();
        orderCreatedDto.setId("4534545345345");
        orderCreatedDto.setAccount("order-service-order");
        return new ResponseEntity<>(orderCreatedDto, HttpStatus.OK);
//        return new ResponseEntity<>(orderService.findOrderById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/account/{accountId}")
    public ResponseEntity<List<OrderCreatedDto>> findOrdersByAccount(@PathVariable("accountId") String accountId){
        return new ResponseEntity<>(orderService.findOrdersByAccountId(accountId), HttpStatus.OK);
    }
}
