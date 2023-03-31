package com.pratiti.project.service;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;
import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.model.TokenData;
import com.pratiti.project.queuemanager.TokenQueueManager;
import com.pratiti.project.repository.ServiceRepository;
import com.pratiti.project.repository.ServicetypeRepository;
import com.pratiti.project.repository.TokenRepository;

@org.springframework.stereotype.Service
public class TokenService {
	
	TokenQueueManager tokenqueue=TokenQueueManager.getInstance();
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	ServicetypeRepository servicetypeRepository;
	
	@Autowired
	TokenRepository tokenRepository;
	
	public Token addToken(TokenData tokenData,int i) {
		
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
		token.setServicetypeId(servicetypeRepository.findByServiceName(tokenData.getSubServices().get(i)).get().getId());
		tokenqueue.enqueue(token, service.getCounter().getId());
		tokenRepository.save(token);
		
		return token;
	}

	public void copyAction(int counterId) {
		Map<Integer,Deque<Token>> pendingMap=tokenqueue.getPendingMap();
		Deque<Token> queue=pendingMap.get(counterId);
		int length=queue.size();
		for(int i=0;i<length;i++) {
			Token pendingToken=tokenqueue.dequeue(counterId,"pendingqueue");
			pendingToken.setStatus(Status.PENDING);
			tokenqueue.enqueue(pendingToken,counterId);
		}
	}
}
