package com.pratiti.project.service;

import org.springframework.stereotype.Service;

import com.pratiti.project.queuemanager.TokenQueueManager;

@Service
public class CounterService {
	
	
	TokenQueueManager tokenqueue=TokenQueueManager.getInstance();
	
}
