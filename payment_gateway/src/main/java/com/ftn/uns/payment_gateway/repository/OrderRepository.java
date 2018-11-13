package com.ftn.uns.payment_gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.payment_gateway.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
