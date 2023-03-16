package com.pratiti.project.model;

import java.util.List;

import com.pratiti.project.entity.Service;
import com.pratiti.project.entity.Servicetype;

public class TokenData {
	
	private int tokenId;
	private int tokenStatus;
	
	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public int getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(int tokenStatus) {
		this.tokenStatus = tokenStatus;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public List<String> getSubServices() {
		return subServices;
	}

	public void setSubServices(List<String> subServices) {
		this.subServices = subServices;
	}

	private String service;
	
	private List<String> subServices;
	
	
	

}
