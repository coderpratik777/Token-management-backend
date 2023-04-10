package com.pratiti.project.controller;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.project.entity.GlobalQueue;
import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.exceptions.CounterServiceException;
import com.pratiti.project.exceptions.TokenServiceException;
import com.pratiti.project.model.AddTokenStatus;
import com.pratiti.project.model.CallNextStatus;
import com.pratiti.project.model.TokenGenerationData;
import com.pratiti.project.queuemanager.TokenQueueManager;
import com.pratiti.project.service.TokenService;

@RestController
@CrossOrigin
public class TokenController {

	@Autowired
	TokenService tokenService;

//	TokenQueueManager tokenManager = TokenQueueManager.getInstance();

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

}
