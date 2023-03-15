package com.pratiti.project.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="service_name")
	private String serviceName;

	//bi-directional many-to-one association to Servicetype
	@OneToMany(mappedBy="service")
	private List<Servicetype> servicetypes;

	//bi-directional many-to-one association to Token
	@OneToMany(mappedBy="service")
	private List<Token> tokens;

	public Service() {
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

	public List<Servicetype> getServicetypes() {
		return this.servicetypes;
	}

	public void setServicetypes(List<Servicetype> servicetypes) {
		this.servicetypes = servicetypes;
	}

	public Servicetype addServicetype(Servicetype servicetype) {
		getServicetypes().add(servicetype);
		servicetype.setService(this);

		return servicetype;
	}

	public Servicetype removeServicetype(Servicetype servicetype) {
		getServicetypes().remove(servicetype);
		servicetype.setService(null);

		return servicetype;
	}

	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setService(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setService(null);

		return token;
	}

}