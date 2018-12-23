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

    PayPalTokenAcquirer acquirer = new PayPalTokenAcquirer();

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
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(o.getMagazine().getIssn()));

        HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        return restTemplate.postForObject(paypalAPI, entity, String.class);
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
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(o.getMagazine().getIssn()));

        HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        return restTemplate.postForObject(paypalAPI, entity, String.class);
    }
}
