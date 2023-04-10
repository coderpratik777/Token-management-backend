package com.pratiti.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PendingQueue {
	@Id
	private int tokenId;
	
	private int servicetypeId;

	public int getServicetypeId() {
		return servicetypeId;
	}

	public void setServicetypeId(int servicetypeId) {
		this.servicetypeId = servicetypeId;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}
}
