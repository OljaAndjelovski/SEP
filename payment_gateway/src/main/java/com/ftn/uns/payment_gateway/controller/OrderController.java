package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.dto.OrderDto;
import com.ftn.uns.payment_gateway.mapper.OrderMapper;
import com.ftn.uns.payment_gateway.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new OrderDto());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(mapper.mapToDTO(orderService.createOrder(mapper.mapFromDTO(dto))));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> executeOrder(@RequestBody OrderDto dto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(new OrderDto());
    }

    @DeleteMapping(value = "/{id}")
    public void cancelOrder(@PathVariable("id") Long id) {

    }
}
