package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.dto.OrderDto;
import com.ftn.uns.payment_gateway.mapper.OrderMapper;
import com.ftn.uns.payment_gateway.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "https://localhost:4200", maxAge = 36000)
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(mapper.mapManyToDTO(orderService.findAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") String id) {
        return ResponseEntity.ok(mapper.mapToDTO(orderService.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@RequestBody OrderDto dto) {
        dto.setMerchantOrderId("1");
        return ResponseEntity.ok(orderService.createOrder(mapper.mapFromDTO(dto)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> executeOrder(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(orderService.updateOrder(mapper.mapFromDTO(dto)));
    }

    @DeleteMapping(value = "/{id}")
    public void cancelOrder(@PathVariable("id") Long id) {

    }
}
