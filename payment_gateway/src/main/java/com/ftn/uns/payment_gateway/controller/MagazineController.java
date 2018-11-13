package com.ftn.uns.payment_gateway.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_gateway.model.Magazine;
import com.ftn.uns.payment_gateway.service.MagazineService;

@RestController
@RequestMapping(value = "/magazines")
public class MagazineController {

	@Autowired
	private MagazineService magazineService;

	@GetMapping(value = "/{magazineId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Magazine> getMagazine(@PathVariable("magazineId") String magazineId) {
		Magazine magazine = magazineService.findById(magazineId);
		if (magazine != null) {
			return new ResponseEntity<Magazine>(magazineService.findById(magazineId), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Magazine>> getMagazines() {
		System.out.println("\n GET MAGAZINE " + "\n");
		ArrayList<Magazine> magazines = (ArrayList<Magazine>) magazineService.findAll();
		if (magazines != null) {
			return new ResponseEntity<Collection<Magazine>>(magazines, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}
