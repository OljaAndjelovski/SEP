package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.model.PaymentType;
import com.ftn.uns.payment_gateway.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/payment-type")
public class PaymentTypeController {

    @Autowired
    PaymentTypeService paymentTypeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentType>> getAll() {
        System.out.println("\n GET PAYMENT TYPES " + "\n");
        return ResponseEntity.ok(paymentTypeService.getAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentType> getOne(@PathVariable("id") Integer id) {
        if (id != null) {
            System.out.println("\n GET PAYMENT TYPE " + id + "\n");
            PaymentType type = paymentTypeService.getById(id);
            if (type != null) {
                return ResponseEntity.ok(type);
            }

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentType> createPaymentType(@RequestBody PaymentType type) {
        System.out.println("\n CREATE PAYMENT TYPE " + "\n");

        if (type.getCode() == null) {
            PaymentType newType = paymentTypeService.createPaymentType(type);
            if (newType != null) {
                return ResponseEntity.ok(newType);
            }

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentType> updatePaymentType(@RequestBody PaymentType type, @PathVariable("id") Integer id) {
        System.out.println("\n UPDATE PAYMENT TYPE " + "\n");

        if (id != null) {
            PaymentType newType = paymentTypeService.updatePaymentType(type, id);
            if (newType != null) {
                return ResponseEntity.ok(newType);
            }

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePaymentType(@PathVariable("id") Integer id) {
        System.out.println("\n UPDATE PAYMENT TYPE " + "\n");

        if (id != null) {
            paymentTypeService.deletePaymentType(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}/subscribe/{magazineId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Magazine> subscribeToPaymentType(@PathVariable("id") Integer typeId,
                                                           @PathVariable("magazineId") String magazineId) {
        System.out.println("\n SUBSCRIBE MAGAZINE " + magazineId + " TO " + typeId + "\n");

        Magazine magazine = paymentTypeService.subscribeToPaymentType(magazineId, typeId);
        if (!magazine.equals(null)) {
            return ResponseEntity.ok(magazine);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}/unsubscribe/{magazineId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Magazine> unsubscribeFromPaymentType(@PathVariable("id") Integer typeId,
                                                               @PathVariable("magazineId") String magazineId) {
        System.out.println("\n UNSUBSCRIBE MAGAZINE " + magazineId + " FROM " + typeId + "\n");

        Magazine magazine = paymentTypeService.unsubscribeFromPaymentType(magazineId, typeId);
        if (!magazine.equals(null)) {
            return ResponseEntity.ok(magazine);
        }

        return ResponseEntity.badRequest().build();
    }
}
