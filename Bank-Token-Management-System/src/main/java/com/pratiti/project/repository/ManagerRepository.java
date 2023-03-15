package com.pratiti.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratiti.project.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
