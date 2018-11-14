package com.ftn.uns.payment_gateway.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.dto.MagazineDto;
import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.PaymentTypeRepository;

@Service
public class MagazineService {

	@Autowired
	private MagazineRepository magazineRepository;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
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
	
	public Magazine createMagazine(MagazineDto magazineDto) {
		Magazine createdMagazine = magazineDto.createMagazine(magazineDto);
		Set<PaymentType> paymentTypes = new HashSet<>();
		for(Integer code : magazineDto.getTypesCode()) {
			PaymentType paymentType = paymentTypeRepository.findById(code).orElse(null);
			paymentTypes.add(paymentType);
		}
		createdMagazine.setTypes(paymentTypes);
		return magazineRepository.save(createdMagazine);
	}
	
	public Magazine updateMagazine(String id, MagazineDto magazineDto) {
		Magazine updatedMagazine = magazineRepository.findById(id).orElse(null);
		updatedMagazine.setIssn(magazineDto.getIssn());
		updatedMagazine.setTitle(magazineDto.getTitle());
		updatedMagazine.setMembership(magazineDto.getMembership());
		updatedMagazine.setMerchantId(magazineDto.getMerchantId());
		updatedMagazine.setMerchantPassword(magazineDto.getMerchantPassword());
		
		Set<PaymentType> paymentTypes = new HashSet<>();
		for(Integer code : magazineDto.getTypesCode()) {
			PaymentType paymentType = paymentTypeRepository.findById(code).orElse(null);
			paymentTypes.add(paymentType);
		}
		updatedMagazine.setTypes(paymentTypes);
		return magazineRepository.save(updatedMagazine);		
	}
}
