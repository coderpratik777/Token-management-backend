package com.pratiti.project.model;

import java.util.List;


public class TokenData {
	
	private int tokenId;
	private int tokenStatus;
	
    private String service;
	
	private List<String> subServices;
	
	
	
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

	

}
