package com.pratiti.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.pratiti.project.entity.CounterExecutive;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CounterExecutiveRepository extends JpaRepository<CounterExecutive, Integer> {

	Optional<CounterExecutive> findByUsername(String username);


}
