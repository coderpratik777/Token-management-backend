package com.pratiti.project.controller;

import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.project.entity.Token;
import com.pratiti.project.model.StatusData;
import com.pratiti.project.service.CounterService;


@RestController
@CrossOrigin
public class CounterController {
	
	
	
	@Autowired
	private CounterService counterService;
	
	
	@GetMapping("/gettokencounters")
	public Queue<Token> getToken(@RequestParam int cid){
		Queue<Token> tokens=counterService.gettoken(cid);
		return tokens;
	}
	
	@GetMapping("/gettopservice")
	public Token getTopService(@RequestParam int cid) {
		System.out.println("get top service called");
		Token token=counterService.gettopservice(cid);
		return token;	
	}
	
	@PostMapping("/changestatus")
	public void changeStatus(@RequestBody StatusData statusdata){
		System.out.println("get change status called");
		 counterService.changestatus(statusdata.getTid(),statusdata.getSt());
	}
			
}
