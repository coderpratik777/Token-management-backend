package com.pratiti.project.model;

import com.pratiti.project.entity.Service;

public class AddServiceStatus extends Status {

	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
