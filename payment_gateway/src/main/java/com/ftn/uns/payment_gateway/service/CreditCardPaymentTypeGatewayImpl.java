package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.model.Order;

public class CreditCardPaymentTypeGatewayImpl implements PaymentTypeGateway {
    @Override
    public Order createOrder(Order o) {
        System.out.println("STARTED CREDITCARD SERVICE");
        System.out.println(o.toString());
        return o;
    }

    @Override
    public Order cancelOrder(Order o) {
        return null;
    }

    @Override
    public Order confirmOrder(Order o) {
        return null;
    }
}
