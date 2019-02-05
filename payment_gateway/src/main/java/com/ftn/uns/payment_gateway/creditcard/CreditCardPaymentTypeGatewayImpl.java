
package com.ftn.uns.payment_gateway.creditcard;

import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_gateway.dto.MerchantDto;
import com.ftn.uns.payment_gateway.dto.UrlAndIdDto;
import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;

public class CreditCardPaymentTypeGatewayImpl implements PaymentTypeGateway {

	/*
	 * @Value("${url.CANCEL_URL}") private String cancel;
	 * 
	 * @Value("${url.SUCCESS_URL}") private String success;
	 * 
	 * @Value("${url.FAILURE_URL}") private String failure;
	 */

	CreditCardService ccs = new CreditCardService();

	@Override
	public String createOrder(Order order) {

		MerchantDto merchantDto = new MerchantDto();
		merchantDto.setAmount(order.getPrice());
		merchantDto.setMerchantPassword(order.getMerchantPassword());
		merchantDto.setMerchantId(order.getMerchantId()); // id prodavca

		merchantDto.setMerchantOrderID(Integer.parseInt(order.getMerchantOrderId()));
		merchantDto.setMerchantTimestamp(order.getMerchantTimestamp());
		/*
		 * merchantDto.setCancelUrl(cancel); merchantDto.setSuccesslUrl(success);
		 * merchantDto.setFailureUrl(failure);
		 */

		try {
			String bankUrl = order.getBankUrl();
			UrlAndIdDto response = null;
			CreditCardService ccs = new CreditCardService();
			RestTemplate restTemplate = ccs.generateRest();
			response = restTemplate.postForObject(bankUrl, merchantDto, UrlAndIdDto.class);

			return response.getPaymentUrl();
			// redirect to stranica za placanje

		} catch (Exception e) {
			e.printStackTrace();
		}

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