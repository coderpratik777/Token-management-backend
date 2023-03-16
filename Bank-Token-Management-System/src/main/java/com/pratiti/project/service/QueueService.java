package com.pratiti.project.service;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.model.TokenData;
import com.pratiti.project.repository.ServiceRepository;

@Service
public class QueueService {
	
	Queue<Token> counter1TokenQueue=new LinkedList<>();
	
	@Autowired
	ServiceRepository serviceRepository;
	
	public Token addToken(TokenData tokenData) {
		
		Token token=new Token();
		token.setFrequencyOfCalling(0);
		token.setStatus(Status.PENDING);
		String timeColonPattern = "hh:mm:ss a";
		DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
		LocalTime colonTime = LocalTime.now();
		System.out.println(timeColonFormatter.format(colonTime));
		token.setGenerationTime(Time.valueOf(colonTime));
		counter1TokenQueue.add(token);
		return token;
	}
}
