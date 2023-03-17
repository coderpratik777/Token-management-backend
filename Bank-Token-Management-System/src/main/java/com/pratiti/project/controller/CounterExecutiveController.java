package com.pratiti.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.project.exceptions.CounterServiceException;
import com.pratiti.project.model.LoginData;					
import com.pratiti.project.service.CounterExecutiveService;

@RestController
@CrossOrigin
public class CounterExecutiveController {
	
	@Autowired
	private CounterExecutiveService  counterExecutiveService;
	
	@PostMapping("/executivelogin")
	public void Login(@RequestBody LoginData loginData) {
		try {
			counterExecutiveService.login(loginData);
		} catch (CounterServiceException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	

}
