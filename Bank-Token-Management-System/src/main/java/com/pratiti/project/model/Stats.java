package com.pratiti.project.model;

public class Stats {
	private int id;
	private String name;
	private int[] tokensServed;
	private float averageServeTime;
	private int[] tokenAbandoned;
	
	public int getId() {
		return id;
	}
	public void setId(int counterId) {
		this.id = counterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String counterName) {
		this.name = counterName;
	}
	public int[] getTokensServed() {
		return tokensServed;
	}
	public void setTokensServed(int[] tokensServed) {
		this.tokensServed = tokensServed;
	}
	public void setAverageServeTime(float averageServeTime) {
		this.averageServeTime = averageServeTime;
	}
	public float getAverageServeTime() {
		return averageServeTime;
	}
	public void setAverageServeTime(int averageServeTime) {
		this.averageServeTime = averageServeTime;
	}
	public int[] getTokenAbandoned() {
		return tokenAbandoned;
	}
	public void setTokenAbandoned(int[] tokenAbandoned) {
		this.tokenAbandoned = tokenAbandoned;
	}
	
}
