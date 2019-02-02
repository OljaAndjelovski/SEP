package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.paypal.PayPalBillingAgreementService;
import com.ftn.uns.payment_gateway.paypal.PayPalBillingPlanService;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SubscribeService {

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    PayPalBillingPlanService billingPlanService;

    @Autowired
    PayPalBillingAgreementService agreementService;

    public String createSubscription(String issn){
        String planID = createPlan(issn);
        return createAgreement(issn, planID);
    }

    private String createPlan(String issn){
        String jsonCreatePlan = billingPlanService.createPlan(issn);
        Gson gson = new Gson();
        Map<String, Object> json = gson.fromJson(jsonCreatePlan, Map.class);
        String planID = json.get("id").toString();
        billingPlanService.activatePlan(planID, issn);
        return planID;
    }

    private String createAgreement(String issn, String planID){
        return agreementService.createAgreement(magazineRepository.getOne(issn), planID);
    }

    public String executeSubscription(String planID, String issn){
        return agreementService.executeAgreement(planID, issn);
    }
}
