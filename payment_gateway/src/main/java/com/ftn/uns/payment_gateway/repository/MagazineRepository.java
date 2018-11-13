package com.ftn.uns.payment_gateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.payment_gateway.model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, String> {
	public Optional<Magazine> findById(String id);
}
