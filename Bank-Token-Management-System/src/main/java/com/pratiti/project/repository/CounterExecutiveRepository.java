package com.pratiti.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pratiti.project.entity.CounterExecutive;

public interface CounterExecutiveRepository extends JpaRepository<CounterExecutive, Integer> {

	public  CounterExecutive findByUsername(String username);
	
	boolean existsByUsername(String username);
	

}
