package com.pratiti.project.entity;

import javax.persistence.*;


@Entity
@NamedQuery(name="Servicetype.findAll", query="SELECT s FROM Servicetype s")
public class Servicetype  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="service_name")
	private String serviceName;

	//bi-directional many-to-one association to Service
	@ManyToOne
	private Service subService;

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

	public Service getSubService() {
		return subService;
	}

	public void setSubService(Service subService) {
		this.subService = subService;
	}

	
}