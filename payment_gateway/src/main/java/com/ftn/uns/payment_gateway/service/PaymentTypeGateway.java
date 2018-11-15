package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.model.Order;

public interface PaymentTypeGateway {

    Order createOrder();
    Order cancelOrder();
    Order confirmOrder();
}
