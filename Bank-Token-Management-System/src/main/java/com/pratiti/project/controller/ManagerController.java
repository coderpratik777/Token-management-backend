package com.pratiti.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pratiti.project.entity.Counter;
import com.pratiti.project.exceptions.ManagerServiceException;
import com.pratiti.project.service.ManagerService;

@RestController
@CrossOrigin
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@PostMapping("/add/counter")
	public String addCounter(@RequestBody Counter counter) {
		try {
		     managerService.addCounter(counter);
		     return "Counter Added Succesfully";
		} catch (ManagerServiceException e) {
			return (e.getMessage());
		}
	}
	
	
	
}
