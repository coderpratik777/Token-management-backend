package com.pratiti.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.project.entity.GlobalQueue;

public interface GlobalQueueRepository extends JpaRepository<GlobalQueue, Integer>{

	@Query(value="SELECT * FROM global_queue where status = \"PENDING\" limit 1",nativeQuery = true)
	public GlobalQueue findFirst();
	
	@Query(value="SELECT * FROM global_queue where status = \"PENDING\" ",nativeQuery = true)
	public List<GlobalQueue> findAllPending();
	
}
