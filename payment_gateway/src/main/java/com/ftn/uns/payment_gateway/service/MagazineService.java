package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.dto.MagazineDto;
import com.ftn.uns.payment_gateway.mapper.MagazineMapper;
import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private MagazineMapper magazineMapper;

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
        Magazine createdMagazine = magazineMapper.mapFromDTO(magazineDto);
        return magazineRepository.save(createdMagazine);
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
