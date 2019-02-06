package com.ftn.uns.payment_gateway.bitcoin;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.paypal.CurrencyConverter;
import com.ftn.uns.payment_gateway.service.MagazineService;
import com.ftn.uns.payment_gateway.service.OrderService;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;

@Service
public class BitcoinPaymentTypeGatewayImpl implements PaymentTypeGateway {

	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public String createOrder(Order o) {
		System.out.println("\n --- BITCOIN SERVICE ---");
		System.out.println(o.toString());

		
		Double ex = CurrencyConverter.excangeRate(o.getCurrency(), "EUR");

		System.out.println("\n ***** " + ex + " CREATE ORDER:  " + "\n");
		OrderBitcoinDto order = new OrderBitcoinDto();
		order.setTitle(o.getMerchandise());
		order.setMerchantId(o.getMerchantId());
		order.setPrice_amount(o.getPrice()*ex); // pazi na cenu
		order.setPrice_currency("EUR");
		order.setReceive_currency("USD");
		order.setCancel_url("https://localhost:4200/#/error");
		order.setSuccess_url("https://localhost:4200/#/success");
		order.setToken(magazineService.findTokenForBitcoin(o.getMagazine()));
		//order.setToken("Q-smRAh_a6nF-NVXJarEt48YyHtNag1iX-__bZwx"); // Ovde ide od magazina token
		
		String url = "https://api-sandbox.coingate.com/v2/orders";

		// set up the basic authentication header
		//String authorizationHeader = "Bearer Q-smRAh_a6nF-NVXJarEt48YyHtNag1iX-__bZwx";
		String authorizationHeader = "Bearer "+ order.getToken();
		
		// setting up the request headers
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.add("Authorization", authorizationHeader);

		// setting up the request body order request entity is created with request body
		// and headers
		HttpEntity<OrderBitcoinDto> requestEntity = new HttpEntity<OrderBitcoinDto>(order, requestHeaders);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<OrderBitcoinResponse> responseEntity = rt.exchange(url, HttpMethod.POST, requestEntity,
				OrderBitcoinResponse.class);

		OrderBitcoinResponse responseOrder = null;
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			responseOrder = responseEntity.getBody();
			System.out.println(Integer.valueOf(responseEntity.getBody().getId()));
			//o.setMerchantTimestamp(Timestamp.parse(responseEntity.getBody().getCreated_at())); // parse timestamp
			o.setMerchantOrderId(responseEntity.getBody().getId());
			o.setMerchantPassword(magazineService.findMerchantPassword(o.getMagazine()));
			o.setIdBitcoin(Integer.parseInt(responseEntity.getBody().getId()));
			orderService.createOrderBitcoin(o);
			return responseOrder.getPayment_url() + "," + Integer.valueOf(responseEntity.getBody().getId());
		}

		return responseEntity.getStatusCode().toString();
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
