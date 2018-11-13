package com.ftn.uns.payment_gateway.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.service.MagazineService;

@RestController
@RequestMapping(value = "/magazines")
public class MagazineController {

	@Autowired
	private MagazineService magazineService;

	@RequestMapping(value = "/{magazineId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Magazine> getMagazine(@PathVariable("magazineId") String magazineId) {
		Magazine magazine = magazineService.findById(magazineId);
		System.out.println("\n GET MAGAZINE " + magazineId + "\n");
		if (magazine != null) {
			return new ResponseEntity<Magazine>(magazineService.findById(magazineId), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/getMagazines", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Magazine>> getMagazines() {
		System.out.println("\n GET MAGAZINES " + "\n");
		ArrayList<Magazine> magazines = (ArrayList<Magazine>) magazineService.findAll();
		if (magazines != null) {
			return new ResponseEntity<Collection<Magazine>>(magazines, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{magazineId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Magazine> deleteAdditionalService(@PathVariable String magazineId) {
		System.out.println("\n DELETE MAGAZINE:  " + magazineId + "\n");
		Magazine deletedMagazine = magazineService.deleteMagazine(magazineId);

		if (deletedMagazine != null) {
			return new ResponseEntity<Magazine>(deletedMagazine, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
