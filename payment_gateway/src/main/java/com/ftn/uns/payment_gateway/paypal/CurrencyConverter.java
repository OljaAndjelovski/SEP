package com.ftn.uns.payment_gateway.paypal;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CurrencyConverter {

    public static Double excangeRate(String fromCurrency, String toCurrency) {
        String url = "https://api-sandbox.coingate.com/v2/rates/merchant/" + fromCurrency + "/" + toCurrency;
        String authorizationHeader = "Bearer Q-smRAh_a6nF-NVXJarEt48YyHtNag1iX-__bZwx";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestHeaders.add("Authorization", authorizationHeader);
        HttpEntity<String> entity = new HttpEntity<>(null, requestHeaders);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<Double> convertedValue = rt.exchange(url, HttpMethod.GET, entity, Double.class);
        return convertedValue.getBody();
    }
}
