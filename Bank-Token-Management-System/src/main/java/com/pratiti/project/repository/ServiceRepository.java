package com.pratiti.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratiti.project.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{
	
	Optional<Service> findByServiceName(String serviceName);

}
