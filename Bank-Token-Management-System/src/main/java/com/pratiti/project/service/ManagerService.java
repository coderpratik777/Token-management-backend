package com.pratiti.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;
import com.pratiti.project.exceptions.ManagerServiceException;
import com.pratiti.project.repository.CounterRepository;
import com.pratiti.project.repository.ServiceRepository;
import com.pratiti.project.repository.ServicetypeRepository;

@org.springframework.stereotype.Service
public class ManagerService {

	@Autowired
	private CounterRepository counterRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServicetypeRepository serviceTypeRepository;

	// Manager :  Adding Counter
	public int addCounter(Counter counter) {
		Optional<Counter> counter1 = counterRepository.findByName(counter.getName());
		if (!counter1.isPresent()) {
			counterRepository.save(counter);
			return counter.getId();
		} else {
			throw new ManagerServiceException("Counter Is already Available");
		}
	}
	
	
	// Manager : Assigning Counter To Service
	public int addService(Service service) {
		Optional<Service>service1 = serviceRepository.findByCounterId(service.getCounter().getId());
		if (!service1.isPresent()) {
			serviceRepository.save(service);
			return service.getId();
		}else {
			throw new ManagerServiceException("you already assign service to this counter");
		}
	}
	
	
	 
	// Manager : Adding types of services to specific services
	public void addServiceType(Servicetype serviceType) {
		Optional<Servicetype>serviceType1 = serviceTypeRepository.findByServiceName(serviceType.getServiceName());
		if(!serviceType1.isPresent()) {
			serviceTypeRepository.save(serviceType);
		}else {
			throw new ManagerServiceException("This Typw Of service already Available in Our System");
		}
	}

}
