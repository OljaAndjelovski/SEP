package com.ftn.uns.payment_gateway.repository;

import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentServiceDetailsRepository extends JpaRepository<PaymentServiceDetails, Long> {
}
