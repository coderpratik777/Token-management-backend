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

import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.model.TokenData;
import com.pratiti.project.queuemanager.TokenQueueManager;
import com.pratiti.project.service.TokenService;

@RestController
@CrossOrigin
public class TokenController {

	@Autowired
	TokenService queueService;

	TokenQueueManager tokenManager = TokenQueueManager.getInstance();

	@PostMapping("/addtoken")
	public List<Token> addToken(@RequestBody TokenData tokenData) {
		List<Token> tokenList = new ArrayList<>();
		int i = 0;
		for (String s : tokenData.getSubServices()) {
			tokenList.add(queueService.addToken(tokenData, i));
			i++;
		}
		return tokenList;
	}

	// this returns map containing queues
	@GetMapping("/gettoken")
	public Map<Integer, Deque<Token>> gettoken() {
		Map<Integer, Deque<Token>> map = tokenManager.getMap();
		for (Map.Entry<Integer, Deque<Token>> x : map.entrySet()) {
			System.out.println(x.getKey());
			Queue<Token> q = x.getValue();
			for (Token y : q) {
				System.out.println(y);
			}
		}
		return map;
	}

	@GetMapping("/getpendingqueue")
	public Queue<Token> getPendingToken(@RequestParam("counterid") int counterId) {
		Map<Integer, Deque<Token>> pendingMap = tokenManager.getPendingMap();
		Queue<Token> q = new LinkedList<>();
		System.out.println("printing the pending map");
		for (Map.Entry<Integer, Deque<Token>> x : pendingMap.entrySet()) {
			System.out.println(x.getKey());
			if (x.getKey() == counterId) {
				q = x.getValue();
				for (Token y : q) {
					System.out.println(y);
				}
			}
		}
		return q;
	}

	@GetMapping("/gettopservicepq")
	public Token getTopservicePendingQueue(@RequestParam("counterid") int counterId) {
		Token pendingToken = tokenManager.dequeue(counterId, "pendingqueue");
		pendingToken.setStatus(Status.ACTIVE);
		tokenManager.addFirst(counterId, pendingToken);
		return pendingToken;

	}

	@GetMapping("/copy-pendingqueue-to-counterqueue")
	public String copyAction(@RequestParam("counterid") int counterId) {
		queueService.copyAction(counterId);
		return "done copying";
	}

	@GetMapping("/get-token-data-from-queue")
	public Token getTokenData(@RequestParam("id") int id) {
		Token token = new Token();
		Map<Integer, Deque<Token>> map = tokenManager.getMap();
		for (Map.Entry<Integer, Deque<Token>> x : map.entrySet()) {
			Queue<Token> q = x.getValue();
			for (Token y : q) {
				if (y.getId() == id) {
					token = y;
				}
			}
		}

		Map<Integer, Deque<Token>> pendingMap = tokenManager.getPendingMap();
		for (Map.Entry<Integer, Deque<Token>> x : pendingMap.entrySet()) {
			Queue<Token> q = x.getValue();
			for (Token y : q) {
				if (y.getId() == id) {
					token = y;
				}
			}

		}
		return token;
	}

}
