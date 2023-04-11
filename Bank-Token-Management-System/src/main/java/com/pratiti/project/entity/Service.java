package com.pratiti.project.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="service_name")
	private String serviceName;

	//bidirectional many-to-one association to Servicetype
	@OneToMany(mappedBy="parentService",cascade = { CascadeType.ALL })
	private List<Servicetype> servicetypes;

	public Service() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<Servicetype> getServicetypes() {
		return servicetypes;
	}

	public void setServicetypes(List<Servicetype> servicetypes) {
		this.servicetypes = servicetypes;
	}


}