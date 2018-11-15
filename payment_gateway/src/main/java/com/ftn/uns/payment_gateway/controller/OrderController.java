package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.dto.OrderDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(new OrderDTO());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto){
        System.out.println("Order placed: " + dto.getAmount() + " at " + LocalDateTime.now());
        return ResponseEntity.ok(new OrderDTO());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> executeOrder(@RequestBody OrderDTO dto, @PathVariable("id") Long id){
        return ResponseEntity.ok(new OrderDTO());
    }

    @DeleteMapping(value = "/{id}")
    public void cancelOrder(@PathVariable("id") Long id){

    }
}
