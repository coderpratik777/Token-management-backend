package com.pratiti.project.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pratiti.project.entity.Counter;
import com.pratiti.project.exceptions.ManagerServiceException;
import com.pratiti.project.repository.CounterRepository;

@Service
public class ManagerService {

	@Autowired
	private CounterRepository counterRepository;

	public void addCounter(Counter counter) {
		Optional<Counter> counter1 = counterRepository.findByName(counter.getName());
		if (!counter1.isPresent()) {
			counterRepository.save(counter);
		} else {
			throw new ManagerServiceException("Counter Is already Available");
		}
	}

}
