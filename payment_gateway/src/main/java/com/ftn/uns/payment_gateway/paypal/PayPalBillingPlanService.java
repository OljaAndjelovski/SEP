package com.ftn.uns.payment_gateway.paypal;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Service
public class PayPalBillingPlanService {

    @Autowired
    PayPalTokenAcquirer acquirer;

    public String createPlan(Order order) {
        Double total = order.getPrice() * CurrencyConverter.excangeRate(order.getCurrency(), "EUR");
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        RestTemplate restTemplate = new RestTemplate();
        String paypalAPI = "https://api.sandbox.paypal.com/v1/payments/billing-plans";
        String defJson = "{" +
                "\"name\": \"Subscription\"," +
                "\"description\": \"Yearly subscription\"," +
                "\"type\": \"FIXED\"," +
                "\"payment_definitions\": [" +
                "{" +
                "\"name\": \"payment\"," +
                "\"type\": \"REGULAR\"," +
                "\"frequency\": \"MONTH\"," +
                "\"frequency_interval\": \"1\"," +
                "\"cycles\": \"12\"," +
                "\"amount\": {" +
                "\"value\": \"" + df.format(total) +"\", \"currency\": \"EUR\"" +
                "}}]," +
                "\"merchant_preferences\": {" +
                "\"return_url\": \"https://localhost:4200/#/confirm?magazine=" + order.getMagazine().getIssn() + "\"," +
                "\"cancel_url\": \"https://localhost:4200/#/cancel\"}}";

        System.out.println(defJson);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(order.getMagazine().getIssn()));

        HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        return restTemplate.postForObject(paypalAPI, entity, String.class);
    }

    public String activatePlan(String planID, String issn){
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String paypalAPI = "https://api.sandbox.paypal.com/v1/payments/billing-plans/"+planID;
        String defJson = "[{\n" +
                "\"op\": \"replace\"," +
                "\"path\": \"/\"," +
                "\"value\": { \"state\": \"ACTIVE\" }}]";

        /*HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        return restTemplate.patchForObject(paypalAPI, entity, String.class);*/

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(issn));

        HttpEntity<String> entity = new HttpEntity<>(defJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(paypalAPI, HttpMethod.PATCH, entity, String.class);
        return response.getBody();
    }
}
