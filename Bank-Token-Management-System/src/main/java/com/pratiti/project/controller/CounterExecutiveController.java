package com.pratiti.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.project.model.CounterExecutiveLoginData;
import com.pratiti.project.model.LoginStatus;
import com.pratiti.project.model.Status;
import com.pratiti.project.service.CounterExecutiveService;

@RestController
@CrossOrigin
public class CounterExecutiveController {
	
	@Autowired
	private CounterExecutiveService  counterExecutiveService;
	
	@PostMapping("/login/counterExecutive")
	public LoginStatus Login(@RequestBody CounterExecutiveLoginData loginData) {
		LoginStatus status = new LoginStatus();
		try {
			int id = counterExecutiveService.loginExecutive(loginData);
			status.setStatus(true);
			status.setMesssageIfAny("Welcome back");
			status.setId(id);
			status.setCounterId(loginData.getCounterId());
		} catch (Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	@PutMapping("/logout/counterExecutive")
	public Status Login(@RequestParam int counterId) {
		Status status = new Status();
		try {
			counterExecutiveService.logoutExecutive(counterId);
			status.setStatus(true);
			status.setMesssageIfAny("bye..");
		} catch (Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}

}
