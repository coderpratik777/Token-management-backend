package com.pratiti.project.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.GlobalQueue;
import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.GlobalQueue.TempStatus;
import com.pratiti.project.entity.PendingQueue;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.exceptions.CounterServiceException;
import com.pratiti.project.repository.CounterRepository;
import com.pratiti.project.repository.GlobalQueueRepository;
import com.pratiti.project.repository.PendingQueueRepository;
import com.pratiti.project.repository.TokenRepository;

@org.springframework.stereotype.Service
public class CounterService {

	private int count = 0;

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	CounterRepository counterRepository;

	@Autowired
	GlobalQueueRepository globalQueueRepository;

	@Autowired
	PendingQueueRepository pendingQueueRepository;

//	TokenQueueManager tokenqueue = TokenQueueManager.getInstance();

	public GlobalQueue callNext(int counterId) throws CounterServiceException {
		GlobalQueue globalToken;
		
		if(counterRepository.findById(counterId).get().getIsActive()==0) {
			throw new CounterServiceException("Counter Not Active");
		}
		
		if(counterRepository.findById(counterId).get().getIsWorking()!=0) {
			throw new CounterServiceException("Please serve the called token first");
		}
		

		if (globalQueueRepository.findAllPending().size() == 0) {
			if (pendingQueueRepository.findAll().size() == 0) {
				throw new CounterServiceException("No tokens available");
			} else {
				globalToken = globalQueueRepository.findById(pendingQueueRepository.findFirst()).get();
				pendingQueueRepository.deleteById(globalToken.getTokenId());
				count = 0;
			}
		} else {
			if (count == 3 && pendingQueueRepository.findAll().size() > 0) {
				globalToken = globalQueueRepository.findById(pendingQueueRepository.findFirst()).get();
				pendingQueueRepository.deleteById(globalToken.getTokenId());
				count = 0;
			} else {
				globalToken = globalQueueRepository.findFirst();
			}
		}
		LocalDateTime currTime = LocalDateTime.now();
		globalToken.setCalledAtTime(currTime);
		globalToken.setFrequencyOfCalling(globalToken.getFrequencyOfCalling() + 1);
		globalToken.setStatus(TempStatus.ACTIVE);
		globalQueueRepository.save(globalToken);
		
		Counter counter = counterRepository.findById(counterId).get();
		counter.setIsWorking(globalToken.getTokenId());
		counterRepository.save(counter);
		
		count++;
		return globalToken;
	}

	public Token serveToken(int counterId) throws CounterServiceException {
		
		if(counterRepository.findById(counterId).isEmpty()) {
			throw new CounterServiceException("Counter Not Found");
		}
		
		Counter counter = counterRepository.findById(counterId).get();
		
		if(counter.getIsActive()==0) {
			throw new CounterServiceException("Counter Not Active");
		}
		
		if(counter.getIsWorking()==0) {
			throw new CounterServiceException("Please call any token first");
		}
		
		if(globalQueueRepository.findById(counter.getIsWorking()).isEmpty()) {
			throw new CounterServiceException("Token not found.");
		}
		
		GlobalQueue globalToken = globalQueueRepository.findById(counter.getIsWorking()).get();
		
		if(globalToken.getStatus() != TempStatus.ACTIVE) {
			throw new CounterServiceException("Token not active.");
		}

		Token token = new Token();
		token.setId(globalToken.getTokenId());
		token.setGenerationTime(globalToken.getGenerationTime());
		token.setStatus(Status.SERVICED);
		token.setServicetypeId(globalToken.getServicetypeId());
		token.setTimesCalled(globalToken.getFrequencyOfCalling());
		token.setCalledAtTime(globalToken.getCalledAtTime());
		token.setServedBy(counter.getIsActive());
		token.setServedAt(counterId);

		LocalDateTime currTime = LocalDateTime.now();

		token.setServeTime((int) Duration.between(globalToken.getCalledAtTime(), currTime).toMinutes());
		tokenRepository.save(token);
		globalQueueRepository.delete(globalToken);
		
		counter.setIsWorking(0);
		counterRepository.save(counter);
		return token;
	}

	public boolean addTokenToPending(int counterId) throws CounterServiceException{
		if(counterRepository.findById(counterId).isEmpty()) {
			throw new CounterServiceException("Counter Not Found");
		}
		
		Counter counter = counterRepository.findById(counterId).get();
		
		if(counter.getIsActive()==0) {
			throw new CounterServiceException("Counter Not Active");
		}
		
		if(counter.getIsWorking()==0) {
			throw new CounterServiceException("Please call any token first");
		}
		
		if(globalQueueRepository.findById(counter.getIsWorking()).isEmpty()) {
			throw new CounterServiceException("Token not found.");
		}
		
		GlobalQueue globalToken = globalQueueRepository.findById(counter.getIsWorking()).get();
		
		if(globalToken.getStatus() != TempStatus.ACTIVE) {
			throw new CounterServiceException("Token not active.");
		}

		counter.setIsWorking(0);
		counterRepository.save(counter);
		
		if (globalToken.getFrequencyOfCalling() == 3) {
			Token token = new Token();
			token.setId(globalToken.getTokenId());
			token.setGenerationTime(globalToken.getGenerationTime());
			token.setStatus(Status.ABANDONED);
			token.setServicetypeId(globalToken.getServicetypeId());
			token.setTimesCalled(globalToken.getFrequencyOfCalling());
			token.setCalledAtTime(globalToken.getCalledAtTime());
			token.setServedBy(counter.getIsActive());
			token.setServedAt(counterId);
			tokenRepository.save(token);
			globalQueueRepository.delete(globalToken);
			return false;
		} else {
			PendingQueue pendingtoken = new PendingQueue();
			pendingtoken.setTokenId(globalToken.getTokenId());
			pendingtoken.setServicetypeId(globalToken.getServicetypeId());
			pendingQueueRepository.save(pendingtoken);

			globalToken.setStatus(TempStatus.NOSHOW);
			globalQueueRepository.save(globalToken);
			return true;
		}
	}

	public List<GlobalQueue> getGlobalQueue() {
		// TODO Auto-generated method stub
		return globalQueueRepository.findAll();
	}

	public List<PendingQueue> getPendingQueue() {
		// TODO Auto-generated method stub
		return pendingQueueRepository.findAll();
	}

	public GlobalQueue getActiveToken(int counterId) {
		if(counterRepository.findById(counterId).isEmpty()) {
			throw new CounterServiceException("Counter Not Found");
		}
		
		Counter counter = counterRepository.findById(counterId).get();
		
		if(counter.getIsActive()==0) {
			throw new CounterServiceException("Counter Not Active");
		}
		
		if(counter.getIsWorking()==0) {
			throw new CounterServiceException("Please call any token first");
		}
		// TODO Auto-generated method stub
		return globalQueueRepository.findById(counterRepository.findById(counterId).get().getIsWorking()).get();
	}

}
