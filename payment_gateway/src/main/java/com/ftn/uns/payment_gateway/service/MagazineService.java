package com.ftn.uns.payment_gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.dto.MagazineDto;
import com.ftn.uns.payment_gateway.mapper.MagazineMapper;
import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;

@Service
public class MagazineService {

	@Autowired
	private MagazineRepository magazineRepository;

	@Autowired
	private MagazineMapper magazineMapper;

	@Autowired
	private PaymentDetailsService paymentDetailsService;

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
		for (PaymentServiceDetails d : magazineDto.getDetails()) {
			System.out.println("\n" + d.getType().toString() + " " + d.getMerchantPassword());

		}
		paymentDetailsService.deleteNullIssn(magazineDto.getIssn());
		Magazine createdMagazine = magazineMapper.mapFromDTO(magazineDto);
		Magazine saved = magazineRepository.save(createdMagazine);

		

		for (PaymentServiceDetails d : magazineDto.getDetails()) {
			paymentDetailsService.subscribeToPaymentType(magazineDto.getIssn(), d);
		}
		//paymentDetailsService.deleteNullIssn(saved.getIssn());
		return magazineRepository.save(saved);
	}

	public Magazine updateMagazine(String id, MagazineDto magazineDto) {
		Magazine oldMagazine = magazineRepository.findById(id).orElse(null);
		Magazine updatedMagazine = magazineMapper.mapFromDTO(magazineDto);

		oldMagazine.setIssn(updatedMagazine.getIssn());
		oldMagazine.setTitle(updatedMagazine.getTitle());
		oldMagazine.setDetails(updatedMagazine.getDetails());

		return magazineRepository.save(oldMagazine);
	}
	
	public String findTokenForBitcoin(Magazine magazine) {
		for(Magazine m : magazineRepository.findAll()) {
			if(m.getIssn().equals(magazine.getIssn())) {
				System.out.println("\nPostoji magazin u bazi");
				for (PaymentServiceDetails d : m.getDetails()) {
					if(d.getType().equals(PaymentType.BITCOIN)) {
						System.out.println("\nPronadjen token " + d.getMerchantID());
						return d.getMerchantID();
						
					}
				}
			}
		}
		return "";
	}
	
	public String findMerchantPassword(Magazine magazine) {
		for(Magazine m : magazineRepository.findAll()) {
			if(m.getIssn().equals(magazine.getIssn())) {
				System.out.println("\nPostoji magazin u bazi");
				for (PaymentServiceDetails d : m.getDetails()) {
					if(d.getType().equals(PaymentType.BITCOIN)) {
						System.out.println("\nPronadjen token " + d.getMerchantID());
						return d.getMerchantPassword();
						
					}
				}
			}
		}
		return "";
	}
}
