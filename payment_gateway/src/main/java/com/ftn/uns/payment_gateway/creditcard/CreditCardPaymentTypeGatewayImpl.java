package com.ftn.uns.payment_gateway.creditcard;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;

public class CreditCardPaymentTypeGatewayImpl implements PaymentTypeGateway{

	@Override
	public String createOrder(Order order) {
		System.out.println("CREDIT CARD");
		return null;
	}

	@Override
	public String cancelOrder(Order order) {
		return null;
	}

	@Override
	public String executeOrder(Order order) {
		return null;
	}

}
