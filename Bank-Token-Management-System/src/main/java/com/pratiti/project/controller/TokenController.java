package com.pratiti.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.project.entity.GlobalQueue;
import com.pratiti.project.exceptions.TokenServiceException;
import com.pratiti.project.model.AddTokenStatus;
import com.pratiti.project.model.CallNextStatus;
import com.pratiti.project.model.Stats;
import com.pratiti.project.model.TokenGenerationData;
import com.pratiti.project.service.TokenService;

@RestController
@CrossOrigin
public class TokenController {

	@Autowired
	TokenService tokenService;

	@PostMapping("/addtoken")
	public AddTokenStatus addToken(@RequestBody TokenGenerationData tokenGenerationData) {
		AddTokenStatus status = new AddTokenStatus();
		try {
			List<GlobalQueue> tokenList = new ArrayList<>();
			for (Integer i : tokenGenerationData.getSubServices()) {
				tokenList.add(tokenService.addToken(i));
			}
			status.setStatus(true);
			status.setMesssageIfAny("Successfully generated token.");
			status.setTokenList(tokenList);
		} catch (TokenServiceException e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}

	@GetMapping("/get-token-info")
	public CallNextStatus getTokenInfo(@RequestParam int id) {
		CallNextStatus status = new CallNextStatus();
		try {
			GlobalQueue globalQueue = tokenService.getTokenInfo(id);
			status.setStatus(true);
			status.setGlobalQueue(globalQueue);
		}catch(Exception e) {
			status.setStatus(false);
			status.setMesssageIfAny(e.getMessage());
		}
		return status;
	}
	
	@GetMapping("/get/stats/counter")
	public List<Stats> getCounterStats() {
		return tokenService.getCounterStats();
	}
	
	@GetMapping("/get/stats/counter-executive")
	public List<Stats> getCounterExecStats() {
		return tokenService.getCounterExecStats();
	}
	
	@GetMapping("/get/stats/date-wise")
	public List<Object> getDateWiseStats() {
		return tokenService.getDateWiseStats();
	}

}
