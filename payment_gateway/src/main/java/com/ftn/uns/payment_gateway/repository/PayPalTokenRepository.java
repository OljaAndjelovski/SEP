package com.ftn.uns.payment_gateway.repository;

import com.ftn.uns.payment_gateway.model.PayPalToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayPalTokenRepository extends JpaRepository<PayPalToken, String> {
}
