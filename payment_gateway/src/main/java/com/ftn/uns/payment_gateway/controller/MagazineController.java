package com.ftn.uns.payment_gateway.controller;

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

}
