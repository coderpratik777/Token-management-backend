package com.pratiti.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.CounterExecutive;
import com.pratiti.project.entity.GlobalQueue;
import com.pratiti.project.entity.GlobalQueue.TempStatus;
import com.pratiti.project.exceptions.TokenServiceException;
import com.pratiti.project.model.Stats;
import com.pratiti.project.repository.CounterExecutiveRepository;
import com.pratiti.project.repository.CounterRepository;
import com.pratiti.project.repository.GlobalQueueRepository;
import com.pratiti.project.repository.ServiceRepository;
import com.pratiti.project.repository.ServicetypeRepository;
import com.pratiti.project.repository.TokenRepository;


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
	CounterExecutiveRepository counterExecutiveRepository;

	@Autowired
	GlobalQueueRepository globalQueueRepository;

	public GlobalQueue addToken(int i) throws TokenServiceException {	
		if (servicetypeRepository.findById(i).isEmpty()) {
			throw new TokenServiceException("No service Type available");
		}
		GlobalQueue token = new GlobalQueue();

		LocalDateTime currTime = LocalDateTime.now();
		token.setServicetypeId(i);
		token.setGenerationTime(currTime);
		token.setStatus(TempStatus.PENDING);

		LocalDateTime expectedTime = currTime
				.plusMinutes((globalQueueRepository.findAll().size() / counterRepository.findAll().size()) * 5);
		token.setExpectedTime(expectedTime);

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

	public List<Stats> getCounterStats() {
		List<Stats> statsList = new ArrayList<>();
		for(Counter c:counterRepository.findAll()) {
			Stats counterStats = new Stats();
			counterStats.setId(c.getId());
			counterStats.setName(c.getCounterName());
			counterStats.setTokensServed(tokenRepository.findServicedByCounter(c.getId()));
			counterStats.setTokenAbandoned(tokenRepository.findAbandonedByCounter(c.getId()));
			if(tokenRepository.findAverageServeTime(c.getId()).isPresent()) {				
				counterStats.setAverageServeTime(tokenRepository.findAverageServeTime(c.getId()).get());
			}else {
				counterStats.setAverageServeTime(0);
			}
			statsList.add(counterStats);
		}
		return statsList;
	}

	public List<Stats> getCounterExecStats() {
		// TODO Auto-generated method stub
		List<Stats> statsList = new ArrayList<>();
		for(CounterExecutive c:counterExecutiveRepository.findAll()) {
			Stats counterExecStats = new Stats();
			counterExecStats.setId(c.getId());
			counterExecStats.setName(c.getUsername());
			counterExecStats.setTokensServed(tokenRepository.findServicedByCounterExec(c.getId()));
			counterExecStats.setTokenAbandoned(tokenRepository.findAbandonedByCounterExec(c.getId()));
			if(tokenRepository.findAverageServeTimeOfExec(c.getId()).isPresent()) {				
				counterExecStats.setAverageServeTime(tokenRepository.findAverageServeTimeOfExec(c.getId()).get());
			}else {
				counterExecStats.setAverageServeTime(0);
			}
			statsList.add(counterExecStats);
		}
		return statsList;
	}

	public List<Object> getDateWiseStats() {
		// TODO Auto-generated method stub
		return tokenRepository.getDatewiseData();
	}

}
