package com.ftn.uns.payment_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;

@Service
public class MagazineService {

	@Autowired
	private MagazineRepository magazineRepository;
	
	public Magazine findById(String id) {
		return magazineRepository.findById(id).orElse(null);
	}
}
