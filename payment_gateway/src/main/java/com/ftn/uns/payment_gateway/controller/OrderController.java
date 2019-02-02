package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.dto.OrderDto;
import com.ftn.uns.payment_gateway.mapper.OrderMapper;
import com.ftn.uns.payment_gateway.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "https://localhost:4200", maxAge = 36000)
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(BitcoinController.class);
    @Autowired
    OrderService orderService;
    @Autowired
    OrderMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        logger.info("\n\t\tUspešno vraćene porudžbine.\n");
        return ResponseEntity.ok(mapper.mapManyToDTO(orderService.findAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") String id) {
        logger.info("\n\t\tUspešno vraćena porudžbina id " + id + ".\n");
        return ResponseEntity.ok(mapper.mapToDTO(orderService.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@RequestBody OrderDto dto) {
        logger.info("\n\t\tUspešno kreirana porudžbina.\n");
        return ResponseEntity.ok(orderService.createOrder(mapper.mapFromDTO(dto)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> executeOrder(@RequestBody OrderDto dto) {
        logger.info("\n\t\tUspešno ažurirana porudžbina.\n");
        return ResponseEntity.ok(orderService.updateOrder(mapper.mapFromDTO(dto)));
    }

    @DeleteMapping(value = "/{id}")
    public void cancelOrder(@PathVariable("id") Long id) {

    }
}
