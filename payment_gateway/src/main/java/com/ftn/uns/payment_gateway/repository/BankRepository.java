package com.ftn.uns.payment_gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftn.uns.payment_gateway.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

	@Query("select b from Bank b where b.merchantId = :merchantId")
	public Bank findByMerchantId(@Param("merchantId") String merchantId);
}
