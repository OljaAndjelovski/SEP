package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.PaymentServiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        //todo
        //create check if magazine already has payment details specified for the type

        details = paymentServiceDetailsRepository.save(details);
        magazine.getDetails().add(details);

        return magazineRepository.save(magazine);
    }

    public Magazine unsubscribeFromPaymentType(String issn, PaymentServiceDetails details) {
        Magazine magazine = magazineRepository.getOne(issn);
        if (magazine == null) {
            return null;
        }

        //todo
        //create getter for paymentdetails from database

        for(PaymentServiceDetails detail: magazine.getDetails()){
            if(detail.getId().equals(details.getId())){
                magazine.getDetails().remove(detail);
                break;
            }
        }

        return magazineRepository.save(magazine);
    }
}
