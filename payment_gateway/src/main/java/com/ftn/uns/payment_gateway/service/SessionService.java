package com.ftn.uns.payment_gateway.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.uns.payment_gateway.model.Session;
import com.ftn.uns.payment_gateway.repository.SessionRepository;

@Service
public class SessionService {
	@Autowired
	private SessionRepository sessionRepository;
	
	public Session findById(Long id) {
		return sessionRepository.findById(id).orElse(null);
	}
	
	public Session createSession(Session session) {
		session.setTimestamp(LocalDateTime.now());
		return sessionRepository.save(session);
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
