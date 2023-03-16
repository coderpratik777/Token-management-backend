package com.pratiti.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.project.entity.Servicetype;

public interface ServicetypeRepository extends JpaRepository<Servicetype, Integer>{
	
	
	@Query("select st from Servicetype st where st.serviceName=?1")
	Optional<Servicetype> findByServiceName(String x);

}