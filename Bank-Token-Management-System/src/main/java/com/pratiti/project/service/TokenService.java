package com.pratiti.project.service;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;
import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.model.TokenData;
import com.pratiti.project.repository.ServiceRepository;
import com.pratiti.project.repository.ServicetypeRepository;
import com.pratiti.project.repository.TokenRepository;

@org.springframework.stereotype.Service
public class TokenService {
	
	Queue<Token> counter1TokenQueue=new LinkedList<>();
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	ServicetypeRepository servicetypeRepository;
	
	@Autowired
	TokenRepository tokenRepository;
	
	public Token addToken(TokenData tokenData) {
		
		Optional<Service> svc=serviceRepository.findByServiceName(tokenData.getService());
		Service service=svc.get();
		List<Servicetype> typeOfServices=new ArrayList<>();
		for(String x:tokenData.getSubServices()) {
			Optional<Servicetype> type=servicetypeRepository.findByServiceName(x);
			typeOfServices.add(type.get());
		}
		service.setServicetypes(typeOfServices);
		Token token=new Token();
		token.setFrequencyOfCalling(0);
		token.setStatus(Status.PENDING);
		//String timeColonPattern = "hh:mm:ss a";
		//DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
		LocalTime colonTime = LocalTime.now();
		//timeColonFormatter.format(colonTime);
		token.setGenerationTime(Time.valueOf(colonTime));
		token.setService(service);
		counter1TokenQueue.add(token);
		tokenRepository.save(token);
		
		return token;
	}
}
