package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.model.Order;

public interface PaymentTypeGateway {

    String createOrder(Order order);
    String cancelOrder(Order order);
    String executeOrder(Order order);
}
