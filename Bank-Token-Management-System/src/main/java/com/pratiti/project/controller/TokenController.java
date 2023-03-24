package com.pratiti.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.project.entity.Token;
import com.pratiti.project.model.TokenData;
import com.pratiti.project.queuemanager.TokenQueueManager;
import com.pratiti.project.service.TokenService;

@RestController
@CrossOrigin
public class TokenController {
	
	@Autowired
	TokenService queueService;
	
	
	TokenQueueManager token=TokenQueueManager.getInstance();
	
	@PostMapping("/addtoken")
	public List<Token> addToken(@RequestBody TokenData tokenData) {
		List<Token> tokenList= new ArrayList<>();
		int i =0;
		for(String s:tokenData.getSubServices()) {
			tokenList.add(queueService.addToken(tokenData,i));
			i++;
		}
		return tokenList;
	}
	
	//this is for testing purpose to get the locally save queues in the console 
	@GetMapping("/gettoken")
	public Map<Integer,Queue<Token>> gettoken() {
		Map<Integer,Queue<Token>> map=token.getMap();
		for(Map.Entry<Integer, Queue<Token>> x:map.entrySet()) {
			System.out.println(x.getKey());
			Queue<Token> q=x.getValue();
			for(Token y:q) {
				System.out.println(y);
			}
		}
		return map;
	}
	
	@GetMapping("/getpendingtoken")
	public Map<Integer,Queue<Token>> getPendingToken() {
		Map<Integer,Queue<Token>> pendingMap=token.getPendingMap();
		System.out.println("printing the pending map");
		for(Map.Entry<Integer, Queue<Token>> x:pendingMap.entrySet()) {
			System.out.println(x.getKey());
			Queue<Token> q=x.getValue();
			for(Token y:q) {
				System.out.println(y);
			}
		}
		return pendingMap;
	}

}
