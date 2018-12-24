package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.service.SubscribeService;
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

    @PostMapping(value = "/subscribe/{issn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createSubscription(@PathVariable String issn) {
        return ResponseEntity.ok(subscribeService.createSubscription(issn));
    }

    @PutMapping(value = "/subscribe/{planID}/execute", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> executeSubscription(@PathVariable String planID){
        return ResponseEntity.ok(/*subscribeService.executeSubscription(planID)*/).body(null);
    }
}
