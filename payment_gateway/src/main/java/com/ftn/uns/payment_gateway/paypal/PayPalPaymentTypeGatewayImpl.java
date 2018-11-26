package com.ftn.uns.payment_gateway.paypal;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;

public class PayPalPaymentTypeGatewayImpl implements PaymentTypeGateway {

    @Override
    public Order createOrder(Order o) {
        
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
