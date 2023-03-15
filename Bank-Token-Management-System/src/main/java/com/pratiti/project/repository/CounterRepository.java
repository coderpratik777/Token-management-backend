package com.pratiti.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratiti.project.entity.Counter;

public interface CounterRepository extends JpaRepository<Counter, Integer>{

}
