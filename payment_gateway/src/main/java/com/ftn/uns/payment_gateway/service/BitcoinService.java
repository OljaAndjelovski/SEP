package com.ftn.uns.payment_gateway.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_gateway.bitcoin.GetOrderDto;
import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.model.PaymentType;
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

	@Scheduled(fixedRate = 30000)
	public void checkOrderStatus() {
		for (Order o : orderRepository.findAll()) { // Ukoliko je bitcoin
			if (o.getType().equals(PaymentType.BITCOIN)) {

				////
				String url = "https://api-sandbox.coingate.com/v2/orders/" + o.getIdBitcoin();
				String authorizationHeader = "Bearer Q-smRAh_a6nF-NVXJarEt48YyHtNag1iX-__bZwx";

				HttpHeaders requestHeaders = new HttpHeaders();
				requestHeaders.setContentType(MediaType.APPLICATION_JSON);
				requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				requestHeaders.add("Authorization", authorizationHeader);
				HttpEntity<String> entity = new HttpEntity<>(null, requestHeaders);

				RestTemplate rt = new RestTemplate();
				ResponseEntity<GetOrderDto> order = rt.exchange(url, HttpMethod.GET, entity, GetOrderDto.class);
				System.out.println("STATUS ORDERA " + order.getBody().getStatus());
				
				
				/////

			}
		}

	}
}
