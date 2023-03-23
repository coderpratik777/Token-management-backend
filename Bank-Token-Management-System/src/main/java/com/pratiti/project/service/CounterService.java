package com.pratiti.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;
import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;
import com.pratiti.project.queuemanager.TokenQueueManager;
import com.pratiti.project.repository.CounterRepository;
import com.pratiti.project.repository.ServiceRepository;
import com.pratiti.project.repository.ServicetypeRepository;
import com.pratiti.project.repository.TokenRepository;

@org.springframework.stereotype.Service
public class CounterService {
	@Autowired
	private TokenRepository tokenrepository;

	@Autowired
	private ServiceRepository  serviceRepository;
	
	@Autowired
	private CounterRepository counterRepository;
	
	@Autowired
	private ServicetypeRepository servicetypeRepository;
	
	TokenQueueManager tokenqueue = TokenQueueManager.getInstance();

	
	
	public Queue<Token> gettoken(int cid) {
		
		Optional<Service>ser=serviceRepository.findByCounterId(cid);
   Service service=ser.get();
   int sid=service.getId();
   
   Queue<Token> q = null;

		Map<Integer, Queue<Token>> map = tokenqueue.getMap();
		for (Map.Entry<Integer, Queue<Token>> x : map.entrySet()) {
			System.out.println(x.getKey());
			if (x.getKey() == sid) {

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
//		tokenqueue.dequeue(cid);
		return token;

	}

	public void changestatus(int cid,String st,int tokenId) {

		Queue<Token> q = new LinkedList<>();
		Token token=new Token();
		token=tokenqueue.top(cid);
		if(st.equals("done"))
		{
			
			token.setStatus(Status.DONE);
			tokenqueue.dequeue(cid);
			System.out.println("done");
		}
		else if(st.equals("pending"))
		{
			token.setStatus(Status.PENDING);
		}
//		Map<Integer, Queue<Token>> map = tokenqueue.getMap();
//		for (Map.Entry<Integer, Queue<Token>> x : map.entrySet()) {
////			System.out.println(x.getKey());
//			if(x.getKey()==cid) {
//				 q = x.getValue();
//				
//				 System.out.println(x.getKey());
//			}	
//		}
		
		
//		for (Token token : q) {
//			System.out.println(tokenId);
//			if(token.getId()==tokenId) {
//				System.out.println(token.getId());
//				if(st.equals("pending"))
//				{
//					token.setStatus(Status.PENDING);
//					System.out.println("pending");
//					
//				}
//				if(st.equals("done")) {
//					token.setStatus(Status.DONE);
//					tokenrepository.save(token);
//					tokenqueue.dequeue(cid);
//					
//					}
//				
//			}
//		}

	}

	public List<Counter> getcounter() {
		
		 return counterRepository.findAll();
		
	}
	
	public Service getservicename(int sid) {
		Optional<Service> ser= serviceRepository.findById(sid);
		Service service=ser.get();
		return service;
	}

	public Servicetype getsubservicename(int sid) {
		
		Optional<Servicetype> sert=servicetypeRepository.findByServiceId(sid);
		
		Servicetype servicetype=sert.get();
		return servicetype;
	}
    
	
	

}
