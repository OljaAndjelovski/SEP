package com.ftn.uns.payment_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.PaymentType;

@Service
public class PaymentTypeGatewayFactory {

    @Autowired
    private ApplicationContext context;

    public boolean checkIfExistingPlugin(PaymentType type) {
        String className = extractClassName(type);
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            return false;
        }

        return true;
    }

    private String extractClassName(PaymentType type) {
        String[] parts = type.name().split("_");
        StringBuilder name = new StringBuilder("");
        for(String part: parts){
            name.append(part.substring(0, 1));
            name.append(part.substring(1, part.length()).toLowerCase());
        }
        return String.format("com.ftn.uns.payment_gateway.%s.%sPaymentTypeGatewayImpl", name.toString().toLowerCase(), name);
    }

    public PaymentTypeGateway getGateway(PaymentType type){
        String className = extractClassName(type);
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }

        return (PaymentTypeGateway) context.getBean(clazz);
    }
}
