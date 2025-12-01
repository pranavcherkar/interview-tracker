package com.lightyagami.interviewtracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lightyagami.interviewtracker.entity.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long>{

	Optional<Connection> findByPersonNameAndCompanyAndRole(String personName,String company, String role);
	
	 boolean existsByPersonNameAndCompanyAndRole(String personName, String company, String role);
}
