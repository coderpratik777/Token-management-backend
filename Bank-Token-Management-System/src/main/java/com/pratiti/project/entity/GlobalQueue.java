package com.pratiti.project.entity;

import java.time.LocalDateTime;

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

	private LocalDateTime generationTime;

	@Enumerated(EnumType.STRING)
	private TempStatus status;
	
	public static enum TempStatus{
		ACTIVE,PENDING,NOSHOW;
	}
	
	private LocalDateTime expectedTime;
	
	private int frequencyOfCalling;
	
	private LocalDateTime calledAtTime;
	
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

	public LocalDateTime getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(LocalDateTime generationTime) {
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

	public LocalDateTime getCalledAtTime() {
		return calledAtTime;
	}

	public void setCalledAtTime(LocalDateTime calledAtTime) {
		this.calledAtTime = calledAtTime;
	}

	public int getServedTime() {
		return servedTime;
	}

	public void setServedTime(int servedTime) {
		this.servedTime = servedTime;
	}

	public LocalDateTime getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(LocalDateTime expectedTime) {
		this.expectedTime = expectedTime;
	}
	
	

}
