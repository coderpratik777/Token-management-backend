package com.pratiti.project.controller;

import java.util.List;

import com.pratiti.project.model.ServiceAndTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.CounterExecutive;
import com.pratiti.project.entity.Manager;
import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;
import com.pratiti.project.exceptions.ManagerServiceException;
import com.pratiti.project.model.AddServiceStatus;
import com.pratiti.project.model.LoginData;
import com.pratiti.project.model.LoginStatus;
import com.pratiti.project.model.Status;
import com.pratiti.project.service.ManagerService;

@RestController
@CrossOrigin
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@PostMapping("/login/manager")
	public LoginStatus managerLogin(@RequestBody LoginData loginData) {
		LoginStatus status=new LoginStatus();
		try {
			Manager manager=managerService.login(loginData);
			status.setStatus(true);
			status.setId(manager.getId());
			status.setMesssageIfAny("Welcome Master");
		} 
		catch (ManagerServiceException e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
//////////////////////////Services CRUD////////////////////////////
	//Adding Service and Service Type
	@PostMapping("/add/service")
	public AddServiceStatus addServiceAndItsType(@RequestBody ServiceAndTypes serviceAndTypes) {
		AddServiceStatus status = new AddServiceStatus();
		try {
			Service service = managerService.addServiceAndItsType(serviceAndTypes);
			status.setStatus(true);
			status.setMesssageIfAny("Services and its types added");
			status.setService(service);
		} catch (Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	//Get services
	@GetMapping("/get/services")
	public List<Service> getAllServices(){
		return managerService.getAllServices();
		
	}
	@GetMapping("/get/subservices-of-service")
	public List<Servicetype> getSubServices(@RequestParam int sId){
		return managerService.getSubServiceFromService(sId);
	}
	
	@GetMapping("/get/allSubServices")
	public String[] getAllSubservices(){
		return managerService.getAllSubServices();
		
	}
	
	//Add sub service
	@PutMapping("/add/subservice")
	public Status addSubservice(@RequestParam String subService, @RequestParam int sId) {
		Status status=new Status();
		try {
			managerService.addSubService(subService,sId);
			status.setStatus(true);
			status.setMesssageIfAny("Successfully added the Subservice!");
		}
		catch(Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	//delete service
	@PutMapping("/delete/service")
	public Status deleteService(@RequestParam int sId) {
		Status status=new Status();
		try {
			managerService.deleteService(sId);
			status.setStatus(true);
			status.setMesssageIfAny("Successfully delete the Service!");
		}
		catch(Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	//delete subservice
	@PutMapping("/delete/subservice")
	public Status deleteSubService(@RequestParam String subService) {
		Status status=new Status();
		try {
			managerService.deleteSubService(subService);
			status.setStatus(true);
			status.setMesssageIfAny("Successfully delete the Subservice!");
		}
		catch(Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
//////////////////////////Counter Executive CRUD////////////////////////////
	@PostMapping("/add/counterexecutive")
	public Status addCounterExecutive(@RequestBody LoginData loginData) {
		Status status=new Status();
		try {
			managerService.addCounterExecutive(loginData);
			status.setStatus(true);
			status.setMesssageIfAny("Successfully added the Counter Executive !");
		}
		catch(Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	@PutMapping("/remove/counterexecutive")
	public Status removeCounterExecutive(@RequestParam int id) {
		Status status=new Status();
		try {
			managerService.removeCounterExecutive(id);
			status.setStatus(true);
			status.setMesssageIfAny("Successfully removed the Counter Executive !");
		}
		catch(Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	@GetMapping("/get/counterExecutives")
	public List<CounterExecutive> getCounterExecutives(){
		 return managerService.getCounterExecutives();
	}
	
//////////////////////////Counter CRUD////////////////////////////
	//Adding Counter 
	@PutMapping("/add/counter")
	public Status addCounter(@RequestParam String name) {
		Status status = new Status();
		try {
			managerService.addCounter(name);
			status.setStatus(true);
			status.setMesssageIfAny("Added counter");
		} catch (Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	@GetMapping("/get/counters")
	public List<Counter> getCounters(){
		 return managerService.getAllCounter();
	}
	
	@PutMapping("/remove/counter")
	public Status removeCounter(@RequestParam int id) {
		Status status=new Status();
		try {
			managerService.removeCounter(id);
			status.setStatus(true);
			status.setMesssageIfAny("Successfully removed the Counter Executive !");
		}
		catch(Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}

}
