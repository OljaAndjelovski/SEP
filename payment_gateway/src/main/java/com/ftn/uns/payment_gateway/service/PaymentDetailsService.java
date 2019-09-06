package com.ftn.uns.payment_gateway.service;

import java.util.ArrayList;
import java.util.Set;

import com.ftn.uns.payment_gateway.config.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.PaymentServiceDetailsRepository;

@Service
public class PaymentDetailsService {

	@Autowired
	PaymentServiceDetailsRepository paymentServiceDetailsRepository;

	@Autowired
	MagazineRepository magazineRepository;

	public Magazine subscribeToPaymentType(String issn, PaymentServiceDetails details) {

		Magazine magazine = magazineRepository.getOne(issn);
		if (magazine == null) {
			return null;
		}

		// todo
		// create check if magazine already has payment details specified for the type
		for(PaymentServiceDetails detail: magazine.getDetails()){
			if(detail.getType() == details.getType()){
				return null;
			}
		}

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		try{
			details.setMerchantPassword(PasswordUtil.encrypt(details.getMerchantPassword()));
		}catch (Exception e) {
			e.printStackTrace();
		}

		details = paymentServiceDetailsRepository.save(details);
		magazine.getDetails().add(details);

		return magazineRepository.save(magazine);
	}

	public Magazine unsubscribeFromPaymentType(String issn, PaymentServiceDetails details) {
		Magazine magazine = magazineRepository.getOne(issn);
		if (magazine == null) {
			return null;
		}

		// todo
		// create getter for paymentdetails from database

		for (PaymentServiceDetails detail : magazine.getDetails()) {
			if (detail.getId().equals(details.getId())) {
				magazine.getDetails().remove(detail);
				break;
			}
		}

		return magazineRepository.save(magazine);
	}

	public void deleteNullIssn(String id) {
		Magazine magazine = magazineRepository.findById(id).orElse(null);
		ArrayList<PaymentServiceDetails> pdd = new ArrayList<PaymentServiceDetails>();

		if (magazine != null) {
			for (PaymentServiceDetails pd : magazine.getDetails()) {
				pdd.add(pd);
			}
			paymentServiceDetailsRepository.deleteAll(pdd);
		}

	}

	public Set<PaymentServiceDetails> getTypesOfMagazine(String issn){
		return magazineRepository.getOne(issn).getDetails();
	}
}
