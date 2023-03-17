package com.pratiti.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.project.entity.Counter;
import com.pratiti.project.entity.CounterExecutive;
import com.pratiti.project.exceptions.CounterServiceException;
import com.pratiti.project.model.LoginData;
import com.pratiti.project.repository.CounterExecutiveRepository;

@Service
public class CounterExecutiveService {

	@Autowired
	private CounterExecutiveRepository counterExecutiveRepository;

	public void login(LoginData logindata) {

		CounterExecutive usern = counterExecutiveRepository.findByUsername(logindata.getUsername());
		if (counterExecutiveRepository.existsByUsername(logindata.getUsername())) {

			if (usern.getPassword().equals(logindata.getPassword())) {
				System.out.println("login successfully");
			} else
				throw new CounterServiceException("Enter Correct Password");

		} else
			throw new CounterServiceException("Enter Correct Username");

	}

}
