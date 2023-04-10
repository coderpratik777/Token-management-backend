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

import com.pratiti.project.entity.GlobalQueue;
import com.pratiti.project.entity.GlobalQueue.TempStatus;
import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;
import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.exceptions.TokenServiceException;
import com.pratiti.project.model.TokenGenerationData;
import com.pratiti.project.queuemanager.TokenQueueManager;
import com.pratiti.project.repository.CounterRepository;
import com.pratiti.project.repository.GlobalQueueRepository;
import com.pratiti.project.repository.ServiceRepository;
import com.pratiti.project.repository.ServicetypeRepository;
import com.pratiti.project.repository.TokenRepository;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;

@org.springframework.stereotype.Service
public class TokenService {

//	TokenQueueManager tokenqueue = TokenQueueManager.getInstance();

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ServicetypeRepository servicetypeRepository;

	@Autowired
	TokenRepository tokenRepository;

	@Autowired
	CounterService counterService;

	@Autowired
	CounterRepository counterRepository;

	@Autowired
	GlobalQueueRepository globalQueueRepository;

	public GlobalQueue addToken(int i) throws TokenServiceException {	
		if (servicetypeRepository.findById(i).isEmpty()) {
			throw new TokenServiceException("No service Type available");
		}
		GlobalQueue token = new GlobalQueue();

		LocalTime currTime = LocalTime.now();
		token.setServicetypeId(i);
		token.setGenerationTime(Time.valueOf(currTime));
		token.setStatus(TempStatus.PENDING);

		LocalTime expectedTime = currTime
				.plusMinutes((globalQueueRepository.findAll().size() / counterRepository.findAll().size()) * 5);
		token.setExpectedTime(Time.valueOf(expectedTime));

		token.setFrequencyOfCalling(0);
		globalQueueRepository.save(token);
		return token;
	}

	public GlobalQueue getTokenInfo(int id) {
		if(globalQueueRepository.findById(id).isEmpty()) {
			throw new TokenServiceException("No such token");
		}
		return globalQueueRepository.findById(id).get();
	}

}
