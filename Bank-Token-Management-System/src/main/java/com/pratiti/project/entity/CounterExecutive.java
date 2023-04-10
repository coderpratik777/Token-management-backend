package com.pratiti.project.entity;

import javax.persistence.*;

@Entity
@Table(name="counter_executive")
@NamedQuery(name="CounterExecutive.findAll", query="SELECT c FROM CounterExecutive c")
public class CounterExecutive  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String password;

	private String username;

	private boolean isLoggedIn;

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public CounterExecutive() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}