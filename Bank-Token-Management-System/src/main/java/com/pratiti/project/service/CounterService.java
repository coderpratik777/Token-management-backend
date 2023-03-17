package com.pratiti.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.queuemanager.TokenQueueManager;
import com.pratiti.project.repository.TokenRepository;

@Service
public class CounterService {
	@Autowired
	private TokenRepository tokenrepository;

	TokenQueueManager tokenqueue = TokenQueueManager.getInstance();

	
	
	public Queue<Token> gettoken(int cid) {

		Queue<Token> q = null;

		Map<Integer, Queue<Token>> map = tokenqueue.getMap();
		for (Map.Entry<Integer, Queue<Token>> x : map.entrySet()) {
			System.out.println(x.getKey());
			if (x.getKey() == cid) {

				q = x.getValue();
			}
		}

		return q;
	}

	public Token gettopservice(int cid) {
		Token token= tokenqueue.top(cid);
		if(token!=null)
		{
			token.setStatus(Status.ACTIVE);
		}
		tokenqueue.dequeue(cid);
		return token;

	}

	public void changestatus(int tid,String st) {

		Queue<Token> q=  new LinkedList<>();

		Map<Integer, Queue<Token>> map = tokenqueue.getMap();
		for (Map.Entry<Integer, Queue<Token>> x : map.entrySet()) {
			System.out.println(x.getKey());
			 q = x.getValue();
			
		}
		for (Token token : q) {
			if(token.getId()==tid) {
				if(st.equals("Pending"))
				{
					token.setStatus(Status.PENDING);
					System.out.println("pending");
					
				}
				if(st.equals("done")) {
					token.setStatus(Status.DONE);
					System.out.println("done");
				}
				
			}
		}

	}

	

}
