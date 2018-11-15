package com.ftn.uns.payment_gateway.mapper;

import com.ftn.uns.payment_gateway.dto.MagazineDto;
import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MagazineMapper {

    public MagazineDto mapToDTO(Magazine magazine){
        MagazineDto dto = new MagazineDto();
        dto.setIssn(magazine.getIssn());
        dto.setMembership(magazine.getMembership());
        dto.setMerchantId(magazine.getMerchantId());
        dto.setTitle(magazine.getTitle());
        dto.setTypesCode(new ArrayList<>());

        for(PaymentType type: magazine.getTypes()){
            dto.getTypesCode().add(type.getCode());
        }

        return dto;
    }
}
