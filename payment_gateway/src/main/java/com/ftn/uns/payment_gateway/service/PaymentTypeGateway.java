package com.ftn.uns.payment_gateway.service;

import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Order;

@Service
public interface PaymentTypeGateway {

    String createOrder(Order order);
    String cancelOrder(Order order);
    String executeOrder(Order order);
}
