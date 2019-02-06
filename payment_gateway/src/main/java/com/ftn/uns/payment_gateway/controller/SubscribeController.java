package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.bitcoin.BitcoinController;
import com.ftn.uns.payment_gateway.dto.OrderDto;
import com.ftn.uns.payment_gateway.mapper.OrderMapper;
import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.SubscribeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("https://localhost:4200")
@RequestMapping("/api")
public class SubscribeController {

    @Autowired
    SubscribeService subscribeService;

    @Autowired
    OrderMapper orderMapper;

	private static final Logger logger = LoggerFactory.getLogger(BitcoinController.class);

    @PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createSubscription(@RequestBody OrderDto orderDto) {
    	logger.info("\n\t\tUspešan subscription na magazin issn " + orderDto.getMerchantId() + ".\n");
        return ResponseEntity.ok(subscribeService.createSubscription(orderMapper.mapFromDTO(orderDto)));
    }

    @PutMapping(value = "/subscribe/{planID}/execute", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity executeSubscription(@PathVariable String planID, @RequestBody String issn){
    	logger.info("\n\t\tUspešan executeSubscription.\n");
    	return ResponseEntity.ok(subscribeService.executeSubscription(planID, issn));
    }
}
