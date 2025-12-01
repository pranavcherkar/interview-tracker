package com.lightyagami.interviewtracker.service;

import java.util.List;

import com.lightyagami.interviewtracker.dto.InterviewRequestDTO;
import com.lightyagami.interviewtracker.dto.InterviewResponseDTO;

public interface InterviewService {

	InterviewResponseDTO createInterview(InterviewRequestDTO request);
	
	 List<InterviewResponseDTO> getAllInterviews();
	 
	 List<InterviewResponseDTO> getInterviewsByConnection(Long connectionId);
	 
	 InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO request);
	 
	 void deleteInterview(Long id);

}
