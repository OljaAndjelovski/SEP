package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.model.PaymentType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/types")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
public class PaymentTypeController {

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
