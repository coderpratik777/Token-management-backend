package com.pratiti.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.project.entity.Token;
import com.pratiti.project.model.TokenData;
import com.pratiti.project.service.TokenService;

@RestController
public class TokenController {
	
	
	@Autowired
	TokenService queueService;
	
	@PostMapping("/addtoken")
	public Token addToken(@RequestBody TokenData tokenData) {
		return queueService.addToken(tokenData);
	}

}
