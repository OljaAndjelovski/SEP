package com.ftn.uns.payment_gateway.bitcoin;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;

public class BitcoinPaymentTypeGatewayImpl implements PaymentTypeGateway {

	@Override
	public String createOrder(Order o) {
		System.out.println("STARTED BITCOIN SERVICE");
		System.out.println(o.toString());

		System.out.println("\n CREATE ORDER:  " + "\n");
		OrderBitcoinDto order = new OrderBitcoinDto();
		// order.setDescription(orderProduct.getDescription());
		order.setMerchantId(o.getMagazine().getIssn()); // Ovde ce ici
		order.setTitle(o.getMagazine().getTitle());
		order.setPrice_amount(o.getPrice());
		order.setOrder_id(o.getMerchantOrderId());

		order.setPrice_currency("EUR"); // Valuta u kojoj placa
		order.setReceive_currency("USD"); // Valuta u kojoj zelim da dobijem
		order.setCancel_url("http://localhost:4200/#/error"); // ako korisnik odustane
		order.setSuccess_url("http://localhost:4200/#/success");
		order.setToken("i6ViVXQyAx-R8CGscCnyCVYg47k7__PG6oUsoRtx");

		String url = "https://api.coingate.com/v2/orders";

		// set up the basic authentication header
		String authorizationHeader = "Bearer i6ViVXQyAx-R8CGscCnyCVYg47k7__PG6oUsoRtx";

		// setting up the request headers
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.add("Authorization", authorizationHeader);

		// setting up the request body
		// order
		// request entity is created with request body and headers
		HttpEntity<OrderBitcoinDto> requestEntity = new HttpEntity<OrderBitcoinDto>(order, requestHeaders);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<OrderBitcoinResponse> responseEntity = rt.exchange(url, HttpMethod.POST, requestEntity,
				OrderBitcoinResponse.class);

		OrderBitcoinResponse responseOrder = null;
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			responseOrder = responseEntity.getBody();
			System.out.println("user response retrieved " + responseOrder.getPayment_url());
		}

		return "";
	}

	@Override
	public String cancelOrder(Order o) {
		return null;
	}

	@Override
	public String executeOrder(Order order) {
		return null;
	}
}
