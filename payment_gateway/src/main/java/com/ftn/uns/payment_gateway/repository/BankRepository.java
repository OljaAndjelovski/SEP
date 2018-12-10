package com.ftn.uns.payment_gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.uns.payment_gateway.model.Bank;

public interface BankRepository extends JpaRepository<Bank,Integer>{

	@Query("select b from Bank where merchantId = :merchantId")
	public Bank findByMerchantId(String merchantId);
}
