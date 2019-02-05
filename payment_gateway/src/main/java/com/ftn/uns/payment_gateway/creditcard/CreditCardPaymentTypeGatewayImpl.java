package com.ftn.uns.payment_gateway.creditcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.uns.payment_gateway.dto.MerchantDto;
import com.ftn.uns.payment_gateway.dto.UrlAndIdDto;
import com.ftn.uns.payment_gateway.model.Order;
import com.ftn.uns.payment_gateway.service.BankService;
import com.ftn.uns.payment_gateway.service.PaymentDetailsService;
import com.ftn.uns.payment_gateway.service.PaymentTypeGateway;

@Service
public class CreditCardPaymentTypeGatewayImpl implements PaymentTypeGateway {

	@Value("${CANCEL_URL}")
	private String cancel;

	@Value("${SUCCESS_URL}")
	private String success;

	@Value("${FAILURE_URL}")
	private String failure;

	@Autowired
	private BankService bankService;
	
	@Autowired
	private PaymentDetailsService paymentDetailsService;

	@Override
	public String createOrder(Order order) {
		
		MerchantDto merchantDto = new MerchantDto();
		merchantDto.setAmount(order.getPrice());
		//merchantDto.setMerchantPassword(paymentDetailsService.getMerchantPasswordByMerchantId(order.getMagazine().getIssn()));
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

		return "https://www.youtube.com/watch?v=lI9jjfphpUU";
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
