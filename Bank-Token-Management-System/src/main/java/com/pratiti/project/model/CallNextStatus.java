package com.pratiti.project.model;

import com.pratiti.project.entity.GlobalQueue;

public class CallNextStatus extends Status{

	private GlobalQueue globalQueue;

	public GlobalQueue getGlobalQueue() {
		return globalQueue;
	}

	public void setGlobalQueue(GlobalQueue globalQueue) {
		this.globalQueue = globalQueue;
	}
}
