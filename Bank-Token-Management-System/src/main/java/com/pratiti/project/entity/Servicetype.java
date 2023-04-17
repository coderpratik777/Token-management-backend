package com.pratiti.project.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = "Servicetype.findAll", query = "SELECT s FROM Servicetype s")
public class Servicetype {
	@Id
	@SequenceGenerator(name = "serviceType_sequence_generator", sequenceName = "serviceType_sequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceType_sequence_generator")
	private int id;

	@Column(name = "service_name")
	private String serviceName;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnore
	private Service parentService;

	public Servicetype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Service getParentService() {
		return parentService;
	}

	public void setParentService(Service parentService) {
		this.parentService = parentService;
	}




}