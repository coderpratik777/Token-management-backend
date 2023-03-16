package com.pratiti.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;
import com.pratiti.project.exceptions.ManagerServiceException;
import com.pratiti.project.service.ManagerService;

@RestController
@CrossOrigin
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	
	// Manager : Adding Counter
	@PostMapping("/add/counter")
	public int addCounter(@RequestBody Counter counter) {
		try {
			int id = managerService.addCounter(counter);
			return id;
		} catch (ManagerServiceException e) {
			return 0;
		}
	}

	
	// Manager : Adding Service
	@PostMapping("/add/service")
	public int addService(@RequestBody Service service) {
		try {
			int id = managerService.addService(service);
			return id;
		} catch (ManagerServiceException e) {
			return 0;
		}
	}

	
	// Manager : Adding Type Of Service
	@PostMapping("/add/service/type")
	public String addServiceType(@RequestBody Servicetype serviceType) {
		try {
			managerService.addServiceType(serviceType);
			return "Service Type Added Successfully!";

		} catch (ManagerServiceException e) {
			return (e.getMessage());
		}
	}
}
