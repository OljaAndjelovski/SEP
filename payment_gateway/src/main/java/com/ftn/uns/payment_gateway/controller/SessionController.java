package com.ftn.uns.payment_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.payment_gateway.model.Session;
import com.ftn.uns.payment_gateway.service.SessionService;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value = "/{sessionId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Session> getSession(@PathVariable("sessionId") Long sessionId) {
		Session session = sessionService.findById(sessionId);
		System.out.println("\n GET SESSION " + sessionId + "\n");
		if (session != null) {
			return new ResponseEntity<Session>(sessionService.findById(sessionId), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> createSession(@RequestBody Session session) {
		System.out.println("\n CREATE SESSION" + "\n");

		Session createdSession = sessionService.createSession(session);

		if (createdSession != null) {
			return new ResponseEntity<String>("http://localhost:8080/sessions?sessionID="+createdSession.getSessionId(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
