package com.pratiti.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.CounterExecutive;
import com.pratiti.project.exceptions.CounterServiceException;
import com.pratiti.project.model.CounterExecutiveLoginData;
import com.pratiti.project.repository.CounterExecutiveRepository;
import com.pratiti.project.repository.CounterRepository;

@Service
public class CounterExecutiveService {

	@Autowired
	private CounterExecutiveRepository counterExecutiveRepository;
	
	@Autowired
	private CounterRepository counterRepository;

	public int loginExecutive(CounterExecutiveLoginData logindata) throws CounterServiceException {
		if (counterExecutiveRepository.findByUsername(logindata.getUsername()).isEmpty()) {
			throw new CounterServiceException("Incorrect Credentials");
		}
		CounterExecutive counterExecutive = counterExecutiveRepository.findByUsername(logindata.getUsername()).get();

		if (counterExecutive.getPassword().equals(logindata.getPassword())) {
			if(counterExecutive.isLoggedIn()) {
				throw new CounterServiceException("Counter Executive Already LoggedIn");
			}
			counterExecutive.setLoggedIn(true);
			Counter counter = counterRepository.findById(logindata.getCounterId()).get();
			counter.setIsActive(counterExecutive.getId());
			counterRepository.save(counter);
			counterExecutiveRepository.save(counterExecutive);
			return counterExecutive.getId();
		} else {
			throw new CounterServiceException("Incorrect Credentials");
		}

	}

	public void logoutExecutive(int counterId) {
		if(counterRepository.findById(counterId).isEmpty()) {
			throw new CounterServiceException("Counter Not Found");
		}
		
		Counter counter = counterRepository.findById(counterId).get();
		
		if(counter.getIsActive()==0) {
			throw new CounterServiceException("Please login before logout");
		}
		
		if (counterExecutiveRepository.findById(counter.getIsActive()).isEmpty()) {
			throw new CounterServiceException("No such executive");
		}
		
		CounterExecutive counterExecutive = counterExecutiveRepository.findById(counter.getIsActive()).get();
		counterExecutive.setLoggedIn(false);
		counterExecutiveRepository.save(counterExecutive);

		counter.setIsActive(0);
		counterRepository.save(counter);
	}

}
