package com.pratiti.project.entity;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GlobalQueue{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tokenId;
	
	private int servicetypeId;

	private Time generationTime;

	@Enumerated(EnumType.STRING)
	private TempStatus status;
	
	public static enum TempStatus{
		ACTIVE,PENDING,NOSHOW;
	}
	
	private Time expectedTime;
	
	private int frequencyOfCalling;
	
	private LocalTime calledAtTime;
	
	private int servedTime;

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public int getServicetypeId() {
		return servicetypeId;
	}

	public void setServicetypeId(int servicetypeId) {
		this.servicetypeId = servicetypeId;
	}

	public Time getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(Time generationTime) {
		this.generationTime = generationTime;
	}

	public TempStatus getStatus() {
		return status;
	}

	public void setStatus(TempStatus status) {
		this.status = status;
	}

	public int getFrequencyOfCalling() {
		return frequencyOfCalling;
	}

	public void setFrequencyOfCalling(int frequencyOfCalling) {
		this.frequencyOfCalling = frequencyOfCalling;
	}

	public LocalTime getCalledAtTime() {
		return calledAtTime;
	}

	public void setCalledAtTime(LocalTime calledAtTime) {
		this.calledAtTime = calledAtTime;
	}

	public int getServedTime() {
		return servedTime;
	}

	public void setServedTime(int servedTime) {
		this.servedTime = servedTime;
	}

	public Time getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(Time expectedTime) {
		this.expectedTime = expectedTime;
	}
	
	

}
