package com.pratiti.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.project.entity.GlobalQueue;
import com.pratiti.project.entity.PendingQueue;
import com.pratiti.project.entity.Token;
import com.pratiti.project.exceptions.CounterServiceException;
import com.pratiti.project.model.CallNextStatus;
import com.pratiti.project.model.ServeTokenStatus;
import com.pratiti.project.model.Status;
import com.pratiti.project.service.CounterService;


@RestController
@CrossOrigin
public class CounterController {
	
	@Autowired
	private CounterService counterService;
	
	
	@GetMapping("/get-global-queue")
	public List<GlobalQueue> getGlobalQueue(){
		return counterService.getGlobalQueue();
	}
	
	@GetMapping("/get-pending-queue")
	public List<PendingQueue> getPendingQueue(){
		return counterService.getPendingQueue();
	}
	
	@GetMapping("/get-active-token-of-counter")
	public CallNextStatus getActiveToken(@RequestParam int counterId){
		CallNextStatus status = new CallNextStatus();
		try {
			GlobalQueue globalQueue = counterService.getActiveToken(counterId);
			status.setStatus(true);
			status.setMesssageIfAny("Next Token Called.");
			status.setGlobalQueue(globalQueue);
		}catch(CounterServiceException e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	@GetMapping("/call-next")
	public CallNextStatus callNext(@RequestParam int counterId) {
		CallNextStatus status = new CallNextStatus();
		try {
			GlobalQueue globalQueue = counterService.callNext(counterId);
			status.setStatus(true);
			status.setMesssageIfAny("Next Token Called.");
			status.setGlobalQueue(globalQueue);
		}catch(CounterServiceException e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	@GetMapping("/serve-token")
	public ServeTokenStatus serveToken(@RequestParam int counterId) {
		ServeTokenStatus status = new ServeTokenStatus();
		try {
			Token token = counterService.serveToken(counterId);
			status.setStatus(true);
			status.setMesssageIfAny("Token Served.");
			status.setToken(token);
		}catch(CounterServiceException e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	@GetMapping("/addtoken-to-pending")
	public Status addTokenToPending(@RequestParam int counterId){
		Status status = new Status();
		try {
			if(counterService.addTokenToPending(counterId)) {
				status.setStatus(true);
				status.setMesssageIfAny("Token added to Pending.");
			}else {
				status.setStatus(false);
				status.setMesssageIfAny("Token Abandoned.");
			}
			
		}catch(CounterServiceException e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	

			
}
