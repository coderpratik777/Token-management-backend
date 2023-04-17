package com.pratiti.project.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.project.entity.Token;
import com.pratiti.project.entity.Token.Status;

public interface TokenRepository extends JpaRepository<Token, Integer>{

	
	@Query("select t from Token t where t.status= ?1")
	public List<Token> fetchByStatus(Status status);
	
	//for counter
	
	@Query(value = "SELECT id FROM banktokendb.token where served_at = ?1 and status=\"SERVICED\"",nativeQuery = true)
	public int[] findServicedByCounter(int counterId);
	
	@Query(value = "SELECT id FROM banktokendb.token where served_at = ?1 and status=\"ABANDONED\"",nativeQuery = true)
	public int[] findAbandonedByCounter(int counterId);
	
	@Query(value = "SELECT avg(serve_time) FROM banktokendb.token where served_at = ?1",nativeQuery = true)
	public Optional<Float> findAverageServeTime(int counterId);
	
	//for Exec
	@Query(value = "SELECT id FROM banktokendb.token where served_by = ?1 and status=\"SERVICED\"",nativeQuery = true)
	public int[] findServicedByCounterExec(int counterExecId);
	
	@Query(value = "SELECT id FROM banktokendb.token where served_by = ?1 and status=\"ABANDONED\"",nativeQuery = true)
	public int[] findAbandonedByCounterExec(int counterExecId);
	
	@Query(value = "SELECT avg(serve_time) FROM banktokendb.token where served_by = ?1",nativeQuery = true)
	public Optional<Float> findAverageServeTimeOfExec(int counterExecId);
	
	//date wise
	
	@Query(value="SELECT cast(called_at_time as date),count(*),avg(serve_time) FROM banktokendb.token where status = \"SERVICED\" group by cast(called_at_time as date)",nativeQuery = true,name = "DateSummaryMapping")
	public List<Object> getDatewiseData();
}
