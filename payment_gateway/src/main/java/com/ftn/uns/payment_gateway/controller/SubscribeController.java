package com.ftn.uns.payment_gateway.controller;

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
    
	private static final Logger logger = LoggerFactory.getLogger(BitcoinController.class);

    @PostMapping(value = "/subscribe/{issn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createSubscription(@PathVariable String issn) {
    	logger.info("\n\t\tUspešan subscription na magazin issn " + issn + ".\n");
        return ResponseEntity.ok(subscribeService.createSubscription(issn));
    }

    @PutMapping(value = "/subscribe/{planID}/execute", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity executeSubscription(@PathVariable String planID, @RequestBody String issn){
    	logger.info("\n\t\tUspešan executeSubscription.\n");
    	return ResponseEntity.ok(subscribeService.executeSubscription(planID, issn));
    }
}
