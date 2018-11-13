package com.ftn.uns.payment_gateway.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Magazine> findAll() {
		return magazineRepository.findAll();
	}
	
	public Magazine deleteMagazine(String id) {
		Magazine deletedMagazine = magazineRepository.findById(id).orElse(null);
		magazineRepository.delete(deletedMagazine);
		return deletedMagazine;
	}
}
