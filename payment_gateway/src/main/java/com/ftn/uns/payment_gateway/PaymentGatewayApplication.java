package com.ftn.uns.payment_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication

public class PaymentGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentGatewayApplication.class, args);
	}
}
