package com.ftn.uns.payment_gateway.paypal;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class PayPalBillingAgreementService {

    @Autowired
    PayPalTokenAcquirer acquirer;

    public String createAgreement(Magazine magazine, String billingPlanId){

        RestTemplate restTemplate = new RestTemplate();
        String paypalAPI = "https://api.sandbox.paypal.com/v1/payments/billing-agreements";
        //TODO change date to something special
        String defJson = "{\n" +
                "  \"name\": \"Agreement-" + magazine.getIssn() + "-"+ LocalDateTime.now() + "\",\n" +
                "  \"description\": \"A yearly subscription to the " + magazine.getTitle() +" magazine\" ,\n" +
                "  \"start_date\": \"2019-02-22T09:13:49+0200\"," +
                "  \"payer\": {\n" +
                "  \"payment_method\": \"paypal\" }," +
                "  \"plan\": { \"id\": \"" +  billingPlanId + "\"}" +
                "}";

        System.out.println(defJson);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(magazine.getIssn()));

        HttpEntity<String> entity = new HttpEntity<String>(defJson, headers);
        return getConfirmAgreementLinkFromResponse(restTemplate.postForObject(paypalAPI, entity, String.class));
    }

    public String executeAgreement(String planID, String issn){
        RestTemplate restTemplate = new RestTemplate();
        String paypalAPI = "https://api.sandbox.paypal.com/v1/payments/billing-agreements/"+planID+"/agreement-execute";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + acquirer.acquireAccessToken(issn));

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        return restTemplate.postForObject(paypalAPI, entity, String.class);
    }

    private String getConfirmAgreementLinkFromResponse(String jsonResponse){
        Gson gson = new Gson();
        JsonObject response = gson.fromJson(jsonResponse, JsonObject.class);
        JsonArray hateoasLinks = response.getAsJsonArray("links");

        for(int i=0; i<hateoasLinks.size(); i++){
           JsonObject hateoasLink = hateoasLinks.get(i).getAsJsonObject();
           if(hateoasLink.get("method").getAsString().equals("REDIRECT")){
               return String.format("{ \"link\": \"%s\"}", hateoasLink.get("href").getAsString());
           }
        }

        return "https://localhost:4200/#/error";
    }
}
