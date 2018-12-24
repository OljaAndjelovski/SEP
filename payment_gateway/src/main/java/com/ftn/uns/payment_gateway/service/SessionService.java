package com.ftn.uns.payment_gateway.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.ftn.uns.payment_gateway.config.JWTTokenProvider;
import com.ftn.uns.payment_gateway.config.SessionToken;
import com.ftn.uns.payment_gateway.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Session;
import com.ftn.uns.payment_gateway.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	MagazineRepository magazineRepository;

	public Session findById(Long id){
		return sessionRepository.getOne(id);
	}

	public String createSession(Session session) {
		session.setTimestamp(LocalDateTime.now());
		if(magazineRepository.getOne(session.getIssn()) == null){
			return null;
		}

		session = sessionRepository.save(session);
		SessionToken token = new SessionToken(session.getId(), session.getUsername());
		JWTTokenProvider tokenProvider = new JWTTokenProvider();
		return tokenProvider.createToken(token);
	}

	@Scheduled(fixedRate = 60000)
	public void clearExpiredSessions() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Starting cleanup: "+now);
		List<Session> expired = StreamSupport.stream(sessionRepository.findAll().spliterator(), false)
			.filter(session -> session.getTimestamp().plusMinutes(30).isBefore(now))
			.collect(Collectors.toList());

		System.out.println("Cleaning " + expired.size() + " sessions");
		sessionRepository.deleteAll(expired);
	}
	
}
