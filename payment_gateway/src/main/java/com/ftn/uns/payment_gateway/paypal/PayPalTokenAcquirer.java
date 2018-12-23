package com.ftn.uns.payment_gateway.paypal;

import com.ftn.uns.payment_gateway.model.PayPalToken;
import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.PayPalTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PayPalTokenAcquirer {

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    PayPalTokenRepository payPalTokenRepository;

    public String acquireAccessToken(String issn) {
        RestTemplate restTemplate = new RestTemplate();

        PayPalToken token = payPalTokenRepository.getOne(issn);
        if(token != null){
            return token.getToken();
        }

        Set<PaymentServiceDetails> details = magazineRepository.getOne(issn).getDetails();
        String client = "";
        String password = "";

        for(PaymentServiceDetails detail: details){
            if(detail.getType() == PaymentType.PAY_PAL){
                client = detail.getMerchantID();
                password = detail.getMerchantPassword();
                break;
            }
        }

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



    @Scheduled(fixedRate = 600000)
    private void cleanSpentTokens(){
        List<PayPalToken> expired = StreamSupport.stream(payPalTokenRepository.findAll().spliterator(), false)
                .filter(token -> token.getValidUntil().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

        System.out.println("Cleaning expired pay pal tokens");
        payPalTokenRepository.deleteAll(expired);
    }
}
