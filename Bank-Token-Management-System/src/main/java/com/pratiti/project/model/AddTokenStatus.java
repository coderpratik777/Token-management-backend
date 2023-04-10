package com.pratiti.project.model;

import java.util.List;

import com.pratiti.project.entity.GlobalQueue;

public class AddTokenStatus extends Status{

	private List<GlobalQueue> tokenList;

	public List<GlobalQueue> getTokenList() {
		return tokenList;
	}

	public void setTokenList(List<GlobalQueue> tokenList) {
		this.tokenList = tokenList;
	}
}
