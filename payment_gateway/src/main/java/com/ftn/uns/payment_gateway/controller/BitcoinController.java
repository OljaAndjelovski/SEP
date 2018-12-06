package com.ftn.uns.payment_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_gateway.bitcoin.BitcoinDto;
import com.ftn.uns.payment_gateway.bitcoin.BitcoinPaymentTypeGatewayImpl;
import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.service.OrderService;
import com.ftn.uns.payment_gateway.service.PaymentTypeGatewayFactory;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api/bitcoinOrder")
public class BitcoinController {

	@Autowired
	private PaymentTypeGatewayFactory paymentTypeGatewayFactory;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/{paymentType}", method = RequestMethod.POST, produces = "application/json")
	public String getType(@PathVariable("paymentType") String paymentType, @RequestBody BitcoinDto bitcoinDto) {
		System.out.println("*********************" + bitcoinDto.getDescription() + " " + bitcoinDto.getPrice());
		System.out.println("\nPayment type: " + paymentType);

		BitcoinPaymentTypeGatewayImpl paymentTypeGateway = (BitcoinPaymentTypeGatewayImpl) paymentTypeGatewayFactory
				.getGateway(PaymentType.BITCOIN);

		Order order = new Order();
		order.setPrice(bitcoinDto.getPrice());
		order.setCurrency(bitcoinDto.getCurrency());
		order.setPrice(bitcoinDto.getPrice());
		order.setQuantity(bitcoinDto.getQuantity());
		order.setType(PaymentType.BITCOIN);
		order.setMerchantOrderId(bitcoinDto.getMerchantId());
		orderService.createOrder(order);

		String status = "\"" + paymentTypeGateway.createOrder(order) + "\"";
		System.out.println("\n " + status);

		/*
		 * RedirectView rv = new RedirectView();
		 * rv.setUrl(paymentTypeGateway.createOrder(order)); return rv;
		 */

		return status;

	}
}