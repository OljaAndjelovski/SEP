package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.bitcoin.BitcoinController;
import com.ftn.uns.payment_gateway.config.JWTTokenProvider;
import com.ftn.uns.payment_gateway.config.SessionToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ftn.uns.payment_gateway.model.Session;
import com.ftn.uns.payment_gateway.service.SessionService;

@RestController
@CrossOrigin(origins = {"https://localhost:4200", "http://localhost:4201", "http://localhost:9010"})
@RequestMapping(value = "/sessions")
public class SessionController {
	@Autowired
	private SessionService sessionService;
	private static final Logger logger = LoggerFactory.getLogger(BitcoinController.class);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Session> getSession(@PathVariable("id") String jwt) {
		JWTTokenProvider tokenProvider = new JWTTokenProvider();
		String decodedToken = tokenProvider.decodeJWT("Bearer" + jwt);
		SessionToken token = tokenProvider.retrieveDataFromToken(decodedToken);
		Session session = sessionService.findById(token.getSessionId());
		System.out.println("\n GET SESSION " + token.getSessionId() + "\n");
		if (session != null) {
			logger.info("\n\t\tUspešno vraćena sesija jtw " + jwt + ".\n");
			return new ResponseEntity<Session>(session, HttpStatus.OK);
		}
		logger.info("\n\t\tNeuspešno vraćanje sesije jtw " + jwt + ".\n");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> createSession(@RequestBody Session session){
		System.out.println("CREATE SESSION");
		String token = sessionService.createSession(session);
		if(token == null){
			logger.info("\n\t\tNeuspešno kreiranje sesije.\n");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		logger.info("\n\t\tUspešno kreiranje sesije jtw " + token + ".\n");
		return ResponseEntity.ok("{ \"token\": \"" + token + "\"}");
	}
	
}
