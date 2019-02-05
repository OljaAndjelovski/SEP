package com.ftn.uns.payment_gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;

@Repository
public interface PaymentServiceDetailsRepository extends JpaRepository<PaymentServiceDetails, Long> {

	@Query("select p from PaymentServiceDetails p where p.merchantID = :merchantId")
	public PaymentServiceDetails findByMerchantId(@Param("merchantId") String merchantId);
}
