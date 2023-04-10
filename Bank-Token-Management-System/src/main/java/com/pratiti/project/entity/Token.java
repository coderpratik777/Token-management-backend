package com.pratiti.project.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Token  {
	@Id
	private int id;

	private Time generationTime;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public static enum Status{
		SERVICED,ABANDONED;
	}

	private int servicetypeId;

	private int timesCalled;
	
	private LocalTime calledAtTime;

	private int servedBy;
	
	private int servedAt;

	private int serveTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(Time generationTime) {
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

	public LocalTime getCalledAtTime() {
		return calledAtTime;
	}

	public void setCalledAtTime(LocalTime calledAtTime) {
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