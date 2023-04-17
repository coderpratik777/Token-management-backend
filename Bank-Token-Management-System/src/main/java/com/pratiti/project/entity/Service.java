package com.pratiti.project.entity;

import javax.persistence.*;


import java.util.List;


@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service  {
	@Id
	@SequenceGenerator(name = "service_sequence_generator", sequenceName = "service_sequence", allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceType_sequence_generator")
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