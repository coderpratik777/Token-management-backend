package com.pratiti.project.entity;

import javax.persistence.*;


@Entity
@NamedQuery(name="Counter.findAll", query="SELECT c FROM Counter c")
public class Counter  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int counterNumber;

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

	public int getName() {
		return this.counterNumber;
	}

	public void setName(int number) {
		this.counterNumber = number;
	}

	public CounterExecutive getCounterExecutive() {
		return this.counterExecutive;
	}

	public void setCounterExecutive(CounterExecutive counterExecutive) {
		this.counterExecutive = counterExecutive;
	}

}