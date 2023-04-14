package com.pratiti.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pratiti.project.model.ServiceAndTypes;

import org.springframework.beans.factory.annotation.Autowired;
import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.CounterExecutive;
import com.pratiti.project.entity.Manager;
import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;
import com.pratiti.project.exceptions.ManagerServiceException;
import com.pratiti.project.model.LoginData;
import com.pratiti.project.repository.CounterExecutiveRepository;
import com.pratiti.project.repository.CounterRepository;
import com.pratiti.project.repository.ManagerRepository;
import com.pratiti.project.repository.ServiceRepository;
import com.pratiti.project.repository.ServicetypeRepository;

@org.springframework.stereotype.Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private CounterRepository counterRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ServicetypeRepository serviceTypeRepository;

	@Autowired
	private CounterExecutiveRepository counterExecutiveRepository;

	// Manager login
	public Manager login(LoginData data) throws ManagerServiceException {
		Optional<Manager> managerList = managerRepository.findByName(data.getUsername());
		if (managerList.isEmpty()) {
			throw new ManagerServiceException("No Manager Found");
		}
		Manager manager = managerList.get();
		if (manager.getPassword().equals(data.getPassword())) {
			return manager;
		}
		throw new ManagerServiceException("Incorrect Credentials");
	}

//////////////////////////Services CRUD////////////////////////////
	// Adding Services and adding it's types
	public Service addServiceAndItsType(ServiceAndTypes serviceAndTypes) throws ManagerServiceException {
		Optional<Service> serviceCheck = serviceRepository.findByServiceName(serviceAndTypes.getServiceName());

		if (serviceCheck.isPresent()) {
			throw new ManagerServiceException("Service Already Present");
		}

		Service service = new Service();
		List<Servicetype> subServices = new ArrayList<>();

		service.setServiceName(serviceAndTypes.getServiceName());

		for (String i : serviceAndTypes.getServiceTypes()) {
			Servicetype st1 = new Servicetype();
			st1.setServiceName(i);
			st1.setParentService(service);
			subServices.add(st1);
		}
		service.setServicetypes(subServices);
		serviceTypeRepository.saveAll(subServices);
		serviceRepository.save(service);

		return service;
	}
	
	//Adding Subservice
	public boolean addSubService(String subService, int sId) throws ManagerServiceException {
		if(serviceRepository.findById(sId).isEmpty()) {
			throw new ManagerServiceException("No such Service");
		}
		
		if(serviceTypeRepository.findByServiceName(subService).isPresent()) {
			throw new ManagerServiceException("Sub service Already present");
		}
		
		Service service = serviceRepository.findById(sId).get();
		
		Servicetype serviceType = new Servicetype();
		serviceType.setServiceName(subService);
		serviceType.setParentService(service);
		
		serviceTypeRepository.save(serviceType);
		return true;
	}

	// Reading services
	public List<Service> getAllServices() {
		return serviceRepository.findAll();
	}

	// Reading sub services from service
	public List<Servicetype> getSubServiceFromService(int sId) throws ManagerServiceException {
		if(serviceRepository.findById(sId).isEmpty()) {
			throw new ManagerServiceException("No such Service");
		}
		return serviceTypeRepository.findByParentServiceId(sId);
	}

	// Reading all sub services
	public String[] getAllSubServices() {
		String[] subServices = new String[serviceTypeRepository.findAll().size()];
		List<Servicetype> subList= serviceTypeRepository.findAll();
		int i=0;
		for(Servicetype s:subList) {
			subServices[i] = s.getServiceName();
			i++;
		}
		return subServices;
	}
	
	//deleting Service
	public boolean deleteService(int sId) {
		if(serviceRepository.findById(sId).isEmpty()) {
			throw new ManagerServiceException("Service is not present");
		}
		serviceRepository.deleteById(sId);
		return true;
		
	}
	
	//deleting subService
	public boolean deleteSubService(String subService) throws ManagerServiceException {
		if(serviceTypeRepository.findByServiceName(subService).isEmpty()) {
			throw new ManagerServiceException("Sub service is not present");
		}
		serviceTypeRepository.deleteById(serviceTypeRepository.findByServiceName(subService).get().getId());
		return true;
	}
	
	

//////////////////////////Counter Executive CRUD////////////////////////////
	// Adding the Counter Executive
	public void addCounterExecutive(LoginData loginData) throws ManagerServiceException {
		if (counterExecutiveRepository.findByUsername(loginData.getUsername()).isPresent()) {
			throw new ManagerServiceException("Counter executive already exists !");
		}
		CounterExecutive counterExecutive = new CounterExecutive();
		counterExecutive.setLoggedIn(false);
		counterExecutive.setUsername(loginData.getUsername());
		counterExecutive.setPassword(loginData.getPassword());

		counterExecutiveRepository.save(counterExecutive);
	}

	// Removing the Counter Executive
	public void removeCounterExecutive(int id) throws ManagerServiceException {
		if (counterExecutiveRepository.findById(id).isEmpty()) {
			throw new ManagerServiceException("Counter executive does not exists !");
		}
		counterExecutiveRepository.deleteById(id);
	}

	// Reading the Counter Executive
	public List<CounterExecutive> getCounterExecutives() {
		return counterExecutiveRepository.findAll();
	}

//////////////////////////Counter CRUD////////////////////////////
	// Adding Counter
	public void addCounter(String name) throws ManagerServiceException {
		if (counterRepository.findByCounterName(name).isPresent()) {
			throw new ManagerServiceException("Counter Is already Available");
		}
		Counter counter = new Counter();
		counter.setCounterName(name);
		counter.setIsActive(0);
		counter.setIsWorking(0);
		counterRepository.save(counter);
	}

	// Reading all counters
	public List<Counter> getAllCounter() {
		return counterRepository.findAll();
	}

	// Deleting counter
	public void removeCounter(int id) throws ManagerServiceException {
		if (counterRepository.findById(id).isEmpty()) {
			throw new ManagerServiceException("Counter does not exists !");
		}
		counterRepository.deleteById(id);
	}

	



}
