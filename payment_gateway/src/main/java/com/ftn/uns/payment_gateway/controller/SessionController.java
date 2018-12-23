package com.ftn.uns.payment_gateway.controller;

import com.ftn.uns.payment_gateway.config.JWTTokenProvider;
import com.ftn.uns.payment_gateway.config.SessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ftn.uns.payment_gateway.model.Session;
import com.ftn.uns.payment_gateway.service.SessionService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = {"https://localhost:4200", "http://localhost:4201"})
@RequestMapping(value = "/sessions")
public class SessionController {
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Session> getSession(@PathVariable("id") String jwt) {
		JWTTokenProvider tokenProvider = new JWTTokenProvider();
		String decodedToken = tokenProvider.decodeJWT("Bearer" + jwt);
		SessionToken token = tokenProvider.retrieveDataFromToken(decodedToken);
		Session session = sessionService.findById(token.getSessionId());
		System.out.println("\n GET SESSION " + token.getSessionId() + "\n");
		if (session != null) {
			return new ResponseEntity<Session>(session, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> createSession(@RequestBody Session session){
		String token = sessionService.createSession(session);
		if(token == null){
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		return ResponseEntity.ok("{ \"token\": \"" + token + "\"}");
	}
	
}
