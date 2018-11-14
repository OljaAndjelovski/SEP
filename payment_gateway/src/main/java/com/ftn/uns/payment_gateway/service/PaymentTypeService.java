package com.ftn.uns.payment_gateway.service;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import com.ftn.uns.payment_gateway.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeService {

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @Autowired
    MagazineRepository magazineRepository;

    public PaymentType getById(Integer id) {
        return paymentTypeRepository.findById(id).orElse(null);
    }

    public List<PaymentType> getAll() {
        return paymentTypeRepository.findAll();
    }

    public PaymentType createPaymentType(PaymentType type) {
        if (type.getName() != null || !type.getName().equals("")) {

            if (checkIfExistingPlugin(type)) {
                return paymentTypeRepository.save(type);
            }
        }

        return null;
    }

    public PaymentType updatePaymentType(PaymentType newType, Integer id) {
        if (newType.getName() != null || !newType.getName().equals("")) {

            if (checkIfExistingPlugin(newType)) {
                PaymentType type = paymentTypeRepository.getOne(id);
                type.setName(newType.getName());
                return paymentTypeRepository.save(type);
            }
        }

        return null;
    }

    private boolean checkIfExistingPlugin(PaymentType type) {
        String className = extractClassName(type);
        Class clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            return false;
        }

        return true;
    }

    private static String extractClassName(PaymentType type) {
        String lowerCaseName = type.getName().substring(0, 1) + type.getName().substring(1).toLowerCase();
        return String.format("com.ftn.uns.payment_gateway.service.%sPaymentTypeGatewayImpl", lowerCaseName);
    }

    public void deletePaymentType(Integer id) {
        paymentTypeRepository.deleteById(id);
    }

    public Magazine subscribeToPaymentType(String merchantId, Integer typeId) {

        Magazine magazine = magazineRepository.getOne(merchantId);
        if (magazine.equals(null)) {
            return null;
        }

        PaymentType type = paymentTypeRepository.getOne(typeId);
        if (type.equals(null)) {
            return null;
        }

        magazine.getTypes().add(type);
        return magazineRepository.save(magazine);
    }

    public Magazine unsubscribeFromPaymentType(String merchantId, Integer typeId) {

        Magazine magazine = magazineRepository.getOne(merchantId);
        if (magazine.equals(null)) {
            return null;
        }

        PaymentType type = paymentTypeRepository.getOne(typeId);
        if (type.equals(null)) {
            return null;
        }

        magazine.getTypes().remove(type);
        return magazineRepository.save(magazine);
    }
}
