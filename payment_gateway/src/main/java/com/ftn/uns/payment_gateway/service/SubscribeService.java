package com.ftn.uns.payment_gateway.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.paypal.PayPalBillingAgreementService;
import com.ftn.uns.payment_gateway.paypal.PayPalBillingPlanService;
import com.ftn.uns.payment_gateway.repository.OrderRepository;
import com.google.gson.Gson;

@Service
public class SubscribeService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PayPalBillingPlanService billingPlanService;

    @Autowired
    PayPalBillingAgreementService agreementService;

    public String createSubscription(Order order) {
        String planID = createPlan(order);
        if (planID != null) {
            order.setMerchantOrderId(planID);
            order.setMerchantTimestamp(LocalDateTime.now());
            order.setType(PaymentType.PAY_PAL);
            String retVal = createAgreement(order, planID);
            orderRepository.save(order);
            return retVal;
        }

        return null;
    }

    private String createPlan(Order order) {
        try {
            String jsonCreatePlan = billingPlanService.createPlan(order);
            Gson gson = new Gson();
            Map<String, Object> json = gson.fromJson(jsonCreatePlan, Map.class);
            String planID = json.get("id").toString();
            billingPlanService.activatePlan(planID, order.getMagazine().getIssn());
            return planID;
        } catch (Exception e) {
            return null;
        }
    }

    private String createAgreement(Order order, String planID) {
        try {
            return agreementService.createAgreement(order, planID);
        }catch (Exception e){
            return null;
        }
    }

    public String executeSubscription(String planID, String issn) {
        try{
            Order order = orderRepository.getOne(planID);
            String retVal = agreementService.executeAgreement(planID, issn);
            if(retVal != null) {
                order.setExecuted(true);
                orderRepository.save(order);
            }
            return retVal;
        }catch (Exception e){
            return null;
        }
    }

    public String checkSubscribtion(String planID) {
        try{
            Order order = orderRepository.getOne(planID);
            return billingPlanService.getPlan(planID, order.getMagazine().getIssn());
        }catch (Exception e){
            return null;
        }
    }
}