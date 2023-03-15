package com.pratiti.project.entity;

import javax.persistence.*;


@Entity
@NamedQuery(name="Counter.findAll", query="SELECT c FROM Counter c")
public class Counter  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional one-to-one association to CounterExecutive
	@OneToOne(mappedBy="counter")
	private CounterExecutive counterExecutive;

	public Counter() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CounterExecutive getCounterExecutive() {
		return this.counterExecutive;
	}

	public void setCounterExecutive(CounterExecutive counterExecutive) {
		this.counterExecutive = counterExecutive;
	}

}