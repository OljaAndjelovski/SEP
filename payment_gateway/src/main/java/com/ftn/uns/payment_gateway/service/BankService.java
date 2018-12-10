package com.ftn.uns.payment_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Bank;
import com.ftn.uns.payment_gateway.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;
	
	public Bank getBankByMerchantId(String merchantId) {
		return bankRepository.findByMerchantId(merchantId);
	}
}
