package com.ftn.uns.payment_gateway.paypal;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Base64;

@Service
public class PayPalPaymentTypeGatewayImpl implements PaymentTypeGateway {

    @Autowired
    PayPalTokenAcquirer acquirer;

    @Override
    public String createOrder(Order o) {
        Double total = o.getPrice() * CurrencyConverter.excangeRate(o.getCurrency(), "EUR") * o.getQuantity();

        RestTemplate restTemplate = new RestTemplate();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String paypalAPI = "https://api.sandbox.paypal.com/v1/payments/payment";
        String defJson = "{\n" +
                "  \"intent\": \"sale\",\n" +
                "  \"redirect_urls\": {\n" +
                "    \"return_url\": \"https://localhost:4200/#/success\",\n" +
                "    \"cancel_url\": \"https://localhost:4200/#/error\"\n" +
                "  },\n" +
                "  \"payer\": {\n" +
                "    \"payment_method\": \"paypal\"\n" +
                "  },\n" +
                "  \"transactions\": [{\n" +
                "    \"amount\": {\n" +
                "      \"total\": \"" + df.format(total) + "\",\n" +
                "      \"currency\": \"EUR\"\n" +
                "    }\n" +
                "  }]\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(o.getMagazine().getIssn()));

        HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        System.out.println(defJson);
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
