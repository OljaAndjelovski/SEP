package com.ftn.uns.payment_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.repository.OrderRepository;

@Service
public class BitcoinService {

	@Autowired
	private OrderRepository orderRepository;

	public void setOrderBitcoinId(String id, Integer idBitcoin) {
		Order o = orderRepository.findById(id).orElse(null);
		System.out.println(o.getMerchantOrderId());
		o.setIdBitcoin(idBitcoin);
		orderRepository.save(o);

	}
}
