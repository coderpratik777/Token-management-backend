package com.pratiti.project.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@NamedQuery(name="Token.findAll", query="SELECT t FROM Token t")
public class Token  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="expected_time")
	private Time expectedTime;

	@Column(name="frequency_of_calling")
	private int frequencyOfCalling;

	@Column(name="generation_time")
	private Time generationTime;

	private String status;

	//bi-directional many-to-one association to Service
	@ManyToOne
	private Service service;

	public Token() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getExpectedTime() {
		return this.expectedTime;
	}

	public void setExpectedTime(Time expectedTime) {
		this.expectedTime = expectedTime;
	}

	public int getFrequencyOfCalling() {
		return this.frequencyOfCalling;
	}

	public void setFrequencyOfCalling(int frequencyOfCalling) {
		this.frequencyOfCalling = frequencyOfCalling;
	}

	public Time getGenerationTime() {
		return this.generationTime;
	}

	public void setGenerationTime(Time generationTime) {
		this.generationTime = generationTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}