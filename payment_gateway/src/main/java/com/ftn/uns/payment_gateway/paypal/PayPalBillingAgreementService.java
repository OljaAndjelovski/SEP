package com.ftn.uns.payment_gateway.paypal;

import com.ftn.uns.payment_gateway.model.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class PayPalBillingAgreementService {

    PayPalTokenAcquirer acquirer = new PayPalTokenAcquirer();

    public String createAgreement(Magazine magazine, String billingPlanId){

        RestTemplate restTemplate = new RestTemplate();
        String paypalAPI = "https://api.sandbox.paypal.com/v1/payments/billing-agreements";
        String defJson = "{\n" +
                "  \"name\": \"Agreement-" + magazine.getIssn() + "-"+ LocalDateTime.now() + "\",\n" +
                "  \"description\": \"A yearly subscription to the " + magazine.getTitle() +" magazine\" ,\n" +
                "  \"start_date\": \"2018-12-22T09:13:49+0200\"," +
                "  \"payer\": {\n" +
                "  \"payment_method\": \"paypal\"," +
                "  \"payer_info\": {" +
                "  \"email\": \"kosticka.jelena-buyer@gmail.com\"" + "}}," +
                "  \"plan\": { \"id\": \"" +  billingPlanId + "\"}" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(magazine.getIssn()));

        HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        String s = restTemplate.postForObject(paypalAPI, entity, String.class);
        return s;
    }

    public String executeAgreement(String planID, String issn){
        RestTemplate restTemplate = new RestTemplate();
        String paypalAPI = "https://api.sandbox.paypal.com/v1/payments/billing-agreement/"+planID+"/agreement-execute";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(issn));

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        return restTemplate.postForObject(paypalAPI, entity, String.class);
    }
}
