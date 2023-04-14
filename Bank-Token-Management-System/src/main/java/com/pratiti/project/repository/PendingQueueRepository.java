package com.pratiti.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pratiti.project.entity.PendingQueue;

public interface PendingQueueRepository extends JpaRepository<PendingQueue, Integer> {

	@Query(value="SELECT * FROM pending_queue limit 1",nativeQuery = true)
	public int findFirst();
}
