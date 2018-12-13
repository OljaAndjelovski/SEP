package com.ftn.uns.payment_gateway.creditcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_gateway.dto.MerchantDto;
import com.ftn.uns.payment_gateway.dto.UrlAndIdDto;
import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.BankService;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;

public class CreditCardPaymentTypeGatewayImpl implements PaymentTypeGateway {

	@Value("${url.CANCEL_URL}")
	private String cancel;

	@Value("${url.SUCCESS_URL}")
	private String success;

	@Value("${url.FAILURE_URL}")
	private String failure;

	@Autowired
	private BankService bankService;

	@Override
	public String createOrder(Order order) {
		MerchantDto merchantDto = new MerchantDto();
		merchantDto.setAmount(order.getPrice());
		//merchant.pass fali
		merchantDto.setMerchantId(order.getMagazine().getIssn());
		merchantDto.setMerchantOrderID(Integer.parseInt(order.getMerchantOrderId()));
		merchantDto.setMerchantTimestamp(order.getMerchantTimestamp());
		merchantDto.setCancelUrl(cancel);
		merchantDto.setSuccesslUrl(success);
		merchantDto.setFailureUrl(failure);

		

		try {
			String bankUrl = bankService.getBankByMerchantId(merchantDto.getMerchantId()).getBank_url();
			UrlAndIdDto response = null;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MerchantDto> entity = new HttpEntity<>(merchantDto, headers);
			response = restTemplate.postForObject(bankUrl, entity, UrlAndIdDto.class);
			
			// redirect to stranica za placanje 

		} catch (Exception e) {
			// TODO: handle exception
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
