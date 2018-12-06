package com.ftn.uns.payment_gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.dto.MagazineDto;
import com.ftn.uns.payment_gateway.mapper.MagazineMapper;
import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.PaymentServiceDetailsRepository;

@Service
public class MagazineService {

	@Autowired
	private MagazineRepository magazineRepository;

	@Autowired
	private MagazineMapper magazineMapper;

	 @Autowired
	PaymentServiceDetailsRepository paymentServiceDetailsRepository;


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

		Magazine createdMagazine = magazineMapper.mapFromDTO(magazineDto);
		Magazine saved = magazineRepository.save(createdMagazine);

		
		for (PaymentServiceDetails d : magazineDto.getDetails()) {
			PaymentServiceDetails psd = new PaymentServiceDetails();
			System.out.println("\n"+ d.getId());
			psd.setMerchantID(d.getMerchantID());
			psd.setMerchantPassword(d.getMerchantPassword());
			psd.setType(d.getType());
			PaymentServiceDetails details = paymentServiceDetailsRepository.save(psd);
			saved.getDetails().add(details);
		}
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
}
