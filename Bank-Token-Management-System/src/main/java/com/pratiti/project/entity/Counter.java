package com.pratiti.project.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name="Counter.findAll", query="SELECT c FROM Counter c")
public class Counter  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String counterName;
	
	private int isActive;
	private int isWorking;
	
	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public Counter() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsWorking() {
		return isWorking;
	}

	public void setIsWorking(int isWorking) {
		this.isWorking = isWorking;
	}


}