package com.investment.api;

import com.investment.api.service.OrderService;
import com.investment.model.dto.OrderCreatedDto;
import com.investment.model.dto.OrderCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('default-roles-my-realm')")
    @GetMapping(value = "/")
    public OrderCreatedDto findOrderById(){
        OrderCreatedDto orderCreatedDto = new OrderCreatedDto();
        orderCreatedDto.setId("4534545345345");
        orderCreatedDto.setAccount("order-service-order");
        return orderCreatedDto;
//        return new ResponseEntity<>(orderService.findOrderById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/account/{accountId}")
    public ResponseEntity<List<OrderCreatedDto>> findOrdersByAccount(@PathVariable("accountId") String accountId){
        return new ResponseEntity<>(orderService.findOrdersByAccountId(accountId), HttpStatus.OK);
    }
}
