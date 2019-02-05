package com.ftn.uns.payment_gateway.controller;

import java.util.ArrayList;
import java.util.Collection;

import com.ftn.uns.payment_gateway.mapper.MagazineMapper;
import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;
import com.ftn.uns.payment_gateway.service.PaymentDetailsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ftn.uns.payment_gateway.dto.MagazineDto;
import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.service.MagazineService;

@RestController
@RequestMapping("/magazines")
@CrossOrigin(origins = "https://localhost:4200", maxAge = 36000)
public class MagazineController {

	@Autowired
	private MagazineService magazineService;

	@Autowired
	private PaymentDetailsService paymentDetailsService;

	@Autowired
	private MagazineMapper magazineMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(BitcoinController.class);

	@RequestMapping(value = "/{magazineId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Magazine> getMagazine(@PathVariable("magazineId") String magazineId) {
		Magazine magazine = magazineService.findById(magazineId);
		System.out.println("\n GET MAGAZINE " + magazineId + "\n");
		if (magazine != null) {
			logger.info("\n\t\tUspešno vraćen magazin id " + magazineId + ".\n");
			return new ResponseEntity<Magazine>(magazineService.findById(magazineId), HttpStatus.OK);
		}
		logger.info("\n\t\tNeuspešno vraćanje magazina sa id " + magazineId +".\n");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/getMagazines", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Magazine>> getMagazines() {
		System.out.println("\n GET MAGAZINES " + "\n");
		ArrayList<Magazine> magazines = (ArrayList<Magazine>) magazineService.findAll();
		if (magazines != null) {
			logger.info("\n\t\tUspešno vraćeni magazini.\n");
			return new ResponseEntity<Collection<Magazine>>(magazines, HttpStatus.OK);
		}
		logger.info("\n\t\tNeuspešno vraćanje magazina.\n");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{magazineId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Magazine> deleteAdditionalService(@PathVariable String magazineId) {
		System.out.println("\n DELETE MAGAZINE:  " + magazineId + "\n");
		Magazine deletedMagazine = magazineService.deleteMagazine(magazineId);

		if (deletedMagazine != null) {
			logger.info("\n\t\tUspešno obrisan magazin id " + magazineId + ".\n");
			return new ResponseEntity<Magazine>(deletedMagazine, HttpStatus.OK);
		}
		logger.info("\n\t\tNeuspešno brisanje magazina sa id " + magazineId + ".\n");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Magazine> createMagazine(@RequestBody MagazineDto magazineDto) {
		System.out.println("\n CREATE MAGAZINE" + "\n");

		Magazine magazine = magazineService.createMagazine(magazineDto);

		if (magazine != null) {
			logger.info("\n\t\tUspešno kreiran magazin sa issn " + magazine.getIssn() + ".\n");
			return new ResponseEntity<Magazine>(magazine, HttpStatus.OK);
		}
		logger.info("\n\t\tNeuspešno kreiranje magazina.\n");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/update/{magazineId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Magazine> updateUser(@PathVariable String magazineId, @RequestBody MagazineDto magazineDto) {
		System.out.println("\n UPDATE MAGAZINE:  " + magazineId + "\n");
		Magazine updatedMagazine = magazineService.updateMagazine(magazineId, magazineDto);

		if (updatedMagazine != null) {
			logger.info("\n\t\tUspešno ažuriran magazin sa issn " + updatedMagazine.getIssn() + ".\n");
			return new ResponseEntity<Magazine>(updatedMagazine, HttpStatus.OK);
		}
		logger.info("\n\t\tNeuspešno ažuriranje magazina.\n");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/{id}/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MagazineDto> subscribeToPaymentType(@RequestBody PaymentServiceDetails details,
			@PathVariable("id") String issn) {
		System.out.println("\n SUBSCRIBE MAGAZINE TO PAYMENT TYPE:  " + issn + "\n");
		Magazine magazine = paymentDetailsService.subscribeToPaymentType(issn, details);

		if (!(magazine == null)) {
			logger.info("\n\t\tUspešno dodavanje " + details.getType().toString() + " načina plaćanja za magazin sa issn " + issn + ".\n");
			return ResponseEntity.ok(magazineMapper.mapToDTO(magazine));
		}
		logger.info("\n\t\tNeuspešno dodavanje " + details.getType().toString() + " načina plaćanja za magazin sa issn " + issn + ".\n");
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}/unsubscribe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MagazineDto> unsubscribeFromPaymentType(@RequestBody PaymentServiceDetails details,
			@PathVariable("id") String issn) {
		System.out.println("\n UNSUBSCRIBE MAGAZINE FROM PAYMENT TYPE:  " + issn + "\n");
		Magazine magazine = paymentDetailsService.unsubscribeFromPaymentType(issn, details);

		if (!(magazine == null)) {
			logger.info("\n\t\tUspešno uklanjanje " + details.getType().toString() + " načina plaćanja za magazin sa issn " + issn + ".\n");
			return ResponseEntity.ok(magazineMapper.mapToDTO(magazine));
		}
		logger.info("\n\t\tNeuspešno uklanjanje " + details.getType().toString() + " načina plaćanja za magazin sa issn " + issn + ".\n");
		return ResponseEntity.noContent().build();
	}
}
