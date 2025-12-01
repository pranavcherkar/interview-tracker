package com.lightyagami.interviewtracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lightyagami.interviewtracker.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long>{

	 List<Interview> findByConnectionId(Long connectionId);
	 
	 boolean existsByConnectionIdAndRoundNumber(Long connectionId, int roundNumber);
	 
	 Optional<Interview> findTopByConnectionIdOrderByRoundNumberDesc(Long connectionId);
	 
}
