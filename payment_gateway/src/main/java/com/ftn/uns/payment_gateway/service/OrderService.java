package com.ftn.uns.payment_gateway.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.OrderRepository;
import com.google.gson.Gson;

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

		order.setExecuted(null);
		order.setStatus("new");
		order.setProductType("paper");

		String orderResponse =  createOrderService(order);
		Gson gson = new Gson();
		Map<String, String> responseMap = (Map<String, String>) gson.fromJson(orderResponse, Map.class);
		order.setMerchantOrderId(responseMap.get("id"));
		order.setStatus(responseMap.get("state"));
		order.setMerchantTimestamp(OffsetDateTime.parse(responseMap.get("create_time")).toLocalDateTime());
		orderRepository.save(order);

		return orderResponse;
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

			return retVal;
		} catch (Exception e) {
			return null;
		}
	}

	private String executeOrderService(Order order) {
		PaymentTypeGateway gateway = paymentTypeGatewayFactory.getGateway(order.getType());
		try {
			String retVal = gateway.executeOrder(order);

			Gson gson = new Gson();
			Map<String, String> responseMap = (Map<String, String>) gson.fromJson(retVal, Map.class);

			Order dbOrder = orderRepository.getOne(order.getMerchantOrderId());
			dbOrder.setExecuted(true);
			dbOrder.setStatus(responseMap.get("state"));
			dbOrder.setBuyerEmail(responseMap.get("buyer_email"));

			orderRepository.save(dbOrder);

			return retVal;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Order> getFromUser(String username) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for (Order o : orderRepository.findAll()) {
			if (o.getPayerId().equals(username) && o.getExecuted() != null && o.getExecuted()) {
				orders.add(o);
			}
		}

		return orders;
	}
}