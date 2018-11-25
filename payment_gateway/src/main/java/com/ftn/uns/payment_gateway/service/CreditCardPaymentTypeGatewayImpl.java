package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.model.Order;

public class CreditCardPaymentTypeGatewayImpl implements PaymentTypeGateway {
    @Override
    public String createOrder(Order o) {
        System.out.println("STARTED CREDITCARD SERVICE");
        System.out.println(o.toString());
        return "";
    }

    @Override
    public String cancelOrder(Order o) {
        return null;
    }

    @Override
    public String executeOrder(Order o) {
        return null;
    }
}
