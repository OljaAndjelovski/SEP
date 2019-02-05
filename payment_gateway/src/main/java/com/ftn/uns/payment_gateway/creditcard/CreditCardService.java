package com.ftn.uns.payment_gateway.creditcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditCardService {

	@Autowired
	@Qualifier("simpleRestTemplate")
	RestTemplate simpleRestTemplate;
	
	public RestTemplate generateRest() {
		return simpleRestTemplate;
	}
}
