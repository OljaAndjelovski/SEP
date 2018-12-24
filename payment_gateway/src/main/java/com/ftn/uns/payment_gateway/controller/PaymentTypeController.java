package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.service.PaymentDetailsService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/types")
@CrossOrigin(origins = "https://localhost:4200", maxAge = 36000)
public class PaymentTypeController {

    @Autowired
    PaymentDetailsService paymentDetailsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPaymentTypes(){
        JsonArray array = new JsonArray();
        Gson gson = new Gson();
        for(PaymentType type: PaymentType.values()){
            StringBuilder json = new StringBuilder("");
            json.append("{\"code\":\"");
            json.append(type.name());
            json.append("\",\"name\":\"");
            json.append(extractClassName(type)+"\"}");
            JsonElement elem = gson.fromJson(json.toString(), JsonElement.class);
            array.add(elem);
        }

        return gson.toJson(array);
    }

    @GetMapping(value = "/{issn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPaymentTypesOfMagazine(@PathVariable("issn") String issn){
        JsonArray array = new JsonArray();
        Gson gson = new Gson();
        Set<PaymentServiceDetails> details = this.paymentDetailsService.getTypesOfMagazine(issn);

        for(PaymentServiceDetails detail: details){
            StringBuilder json = new StringBuilder("");
            json.append("{\"code\":\"");
            json.append(detail.getType().name());
            json.append("\",\"name\":\"");
            json.append(extractClassName(detail.getType())+"\"}");
            JsonElement elem = gson.fromJson(json.toString(), JsonElement.class);
            array.add(elem);
        }

        return gson.toJson(array);
    }

    private static String extractClassName(PaymentType type) {
        String[] parts = type.name().split("_");
        StringBuilder name = new StringBuilder("");
        for(String part: parts){
            name.append(part.substring(0, 1));
            name.append(part.substring(1, part.length()).toLowerCase());
        }

        return name.toString();
    }
}
