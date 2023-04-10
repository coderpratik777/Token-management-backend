package com.pratiti.project.model;

import com.pratiti.project.entity.Token;

public class ServeTokenStatus extends Status{
	private Token token;

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
}
