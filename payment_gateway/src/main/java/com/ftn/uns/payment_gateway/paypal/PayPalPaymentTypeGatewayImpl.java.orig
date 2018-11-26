package com.ftn.uns.payment_gateway.paypal;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

public class PayPalPaymentTypeGatewayImpl implements PaymentTypeGateway {

    @Override
    public String createOrder(Order o) {
        Double total = o.getPrice() /*+ o.getQuantity()*/;

        RestTemplate restTemplate = new RestTemplate();
        String paypalAPI = "https://api.sandbox.paypal.com/v1/payments/payment";
        String defJson = "{\n" +
                "  \"intent\": \"sale\",\n" +
                "  \"redirect_urls\": {\n" +
                "    \"return_url\": \"http://localhost:4200/#/success\",\n" +
                "    \"cancel_url\": \"http://localhost:4200/#/error\"\n" +
                "  },\n" +
                "  \"payer\": {\n" +
                "    \"payment_method\": \"paypal\"\n" +
                "  },\n" +
                "  \"transactions\": [{\n" +
                "    \"amount\": {\n" +
                "      \"total\": \"" + total + "\",\n" +
                "      \"currency\": \"EUR\"\n" +
                "    }\n" +
                "  }]\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquireAccessToken());

        HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        return restTemplate.postForObject(paypalAPI, entity, String.class);
    }

    private String acquireAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        String client = "AS7kftlYLLaUYwYyYJMsW3K3piIompuO7yJl_5n7YbCBXTjW0WS17IahnEFoauQTOnc5kdrg1YeHHMQY";
        String password = "EHtnw-aUi2UfILnFUhzF0fzUTCe5cWxAT6m2K2LJRiWxNqDHjfklZzLPBx6y_HbnEpOIgaPo1zNQ6Foh";
        String paypalAPI = "";
        try {
            paypalAPI = "https://api.sandbox.paypal.com/v1/oauth2/token?" + URLEncoder.encode("grant_type", "UTF-8")
                    + "=" + URLEncoder.encode("client_credentials", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString(
                String.format("%s:%s", client, password).getBytes()));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonResponse = restTemplate.postForObject(paypalAPI, entity, String.class);
        java.util.Map<String, Object> objectMap = JsonParserFactory.getJsonParser().parseMap(jsonResponse);

        return objectMap.get("access_token").toString();
    }

    @Override
    public String cancelOrder(Order o) {
        return null;
    }

    @Override
    public String executeOrder(Order o) {
        RestTemplate restTemplate = new RestTemplate();
        String paypalAPI = "";
        try {
            paypalAPI = "https://api.sandbox.paypal.com/v1/payments/payment/"+URLEncoder.encode(o.getMerchantOrderId(), "UTF-8") + "/execute";
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        String defJson = "{\"payer_id\": \"" + o.getPayerId() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquireAccessToken());

        HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        return restTemplate.postForObject(paypalAPI, entity, String.class);
    }
}
