package com.pratiti.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratiti.project.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer>{

}
