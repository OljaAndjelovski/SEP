package com.ftn.uns.payment_gateway.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.OrderRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	MagazineRepository magazineRepository;

	@Autowired
	PaymentDetailsService paymentDetailsService;

	@Autowired
	PaymentTypeGatewayFactory paymentTypeGatewayFactory;

	public Order findById(String id) {
		return orderRepository.findById(id).orElse(null);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public List<Order> findAllFromUser(String username) {
		return orderRepository.findAllByPayerId(username);
	}

	public void deleteOrder(String id) {
		orderRepository.deleteById(id);
	}

	public String createOrder(Order order) {

		order.setMerchantTimestamp(LocalDateTime.now());
		order.setExecuted(null);
		order.setStatus("new");
//		order = orderRepository.save(order);

		return createOrderService(order);
	}

	public String updateOrder(Order order) {
		/*
		 * if (id.equals(null)) { return null; }
		 * 
		 * Order newOrder = orderRepository.getOne(id); if (newOrder.equals(null)) {
		 * return null; }
		 * 
		 * newOrder.setMerchantTimestamp(order.getMerchantTimestamp());
		 * 
		 * if (order.getPrice().equals(null)) { return null; }
		 * newOrder.setPrice(order.getPrice());
		 * 
		 * if (order.getPayerId().equals(null)) { return null; }
		 * newOrder.setPayerId(order.getPayerId());
		 * 
		 * newOrder.setMagazine(order.getMagazine());
		 */

		return executeOrderService(order);
	}

	public Order createOrderBitcoin(Order o) {
		 return orderRepository.save(o);
	}
	private String createOrderService(Order order) {
		PaymentTypeGateway gateway = paymentTypeGatewayFactory.getGateway(order.getType());

		try {
			String retVal = gateway.createOrder(order);
			orderRepository.save(order);
			return retVal;
		} catch (Exception e) {
			return null;
		}
	}

	private String executeOrderService(Order order) {
		PaymentTypeGateway gateway = paymentTypeGatewayFactory.getGateway(order.getType());
		try {
			String retVal = gateway.executeOrder(order);
			Order dbOrder = orderRepository.getOne(order.getMerchantOrderId());
			dbOrder.setExecuted(true);
			orderRepository.save(dbOrder);
			return retVal;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Order> getFromUser(String username) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order o : orderRepository.findAll()) {
			if (o.getBuyerEmail().equals(username) && o.getExecuted()==true) {
				orders.add(o);
			}
		}

		return orders;
	}
}
