package com.pratiti.project.entity;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Token  {
	@Id
	private int id;

	private LocalDateTime generationTime;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public static enum Status{
		SERVICED,ABANDONED;
	}

	private int servicetypeId;

	private int timesCalled;
	
	private LocalDateTime calledAtTime;

	private int servedBy;
	
	private int servedAt;

	private int serveTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(LocalDateTime generationTime) {
		this.generationTime = generationTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getServicetypeId() {
		return servicetypeId;
	}

	public void setServicetypeId(int servicetypeId) {
		this.servicetypeId = servicetypeId;
	}

	public int getTimesCalled() {
		return timesCalled;
	}

	public void setTimesCalled(int timesCalled) {
		this.timesCalled = timesCalled;
	}

	public LocalDateTime getCalledAtTime() {
		return calledAtTime;
	}

	public void setCalledAtTime(LocalDateTime calledAtTime) {
		this.calledAtTime = calledAtTime;
	}

	public int getServedBy() {
		return servedBy;
	}

	public void setServedBy(int servedBy) {
		this.servedBy = servedBy;
	}

	public int getServedAt() {
		return servedAt;
	}

	public void setServedAt(int servedAt) {
		this.servedAt = servedAt;
	}

	public int getServeTime() {
		return serveTime;
	}

	public void setServeTime(int serveTime) {
		this.serveTime = serveTime;
	}
	
}