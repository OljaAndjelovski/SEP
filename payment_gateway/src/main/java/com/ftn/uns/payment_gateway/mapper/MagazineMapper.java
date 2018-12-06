package com.ftn.uns.payment_gateway.mapper;

import com.ftn.uns.payment_gateway.dto.MagazineDto;
import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import com.ftn.uns.payment_gateway.repository.PaymentServiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class MagazineMapper {

    @Autowired
    PaymentServiceDetailsRepository paymentServiceDetailsRepository;

    public MagazineDto mapToDTO(Magazine magazine) {

        MagazineDto dto = new MagazineDto();
        dto.setIssn(magazine.getIssn());
        dto.setTitle(magazine.getTitle());
        dto.setDetails(new HashSet<>());
/*
        for(PaymentServiceDetails details: magazine.getDetails()){
            dto.getDetails().add(details.getId());
        }
*/
        return dto;
    }

    public Magazine mapFromDTO(MagazineDto dto){
        Magazine magazine = new Magazine();

        magazine.setIssn(dto.getIssn());
        magazine.setTitle(dto.getTitle());
        magazine.setDetails(new HashSet<>());
/*
        for(Long id: dto.getDetails()){
            magazine.getDetails().add(paymentServiceDetailsRepository.getOne(id));
        }*/

        return magazine;
    }
}
