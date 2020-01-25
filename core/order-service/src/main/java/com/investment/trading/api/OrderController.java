package com.investment.trading.api;

import com.investment.trading.model.dto.OrderCreatedDto;
import com.investment.trading.model.dto.OrderCreationDto;
import com.investment.trading.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreatedDto> create(@RequestBody OrderCreationDto orderCreationDto) {
        throw new RuntimeException("Seems error happened");
//       return new ResponseEntity<>(orderService.newOrder(orderCreationDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderCreatedDto> findOrderById(@PathVariable("id") String id){
        return new ResponseEntity<>(orderService.findOrderById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/account/{accountId}")
    public ResponseEntity<List<OrderCreatedDto>> findOrdersByAccount(@PathVariable("accountId") String accountId){
        return new ResponseEntity<>(orderService.findOrdersByAccountId(accountId), HttpStatus.OK);
    }
}
