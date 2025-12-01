package com.lightyagami.interviewtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lightyagami.interviewtracker.dto.ApiResponse;
import com.lightyagami.interviewtracker.dto.InterviewRequestDTO;
import com.lightyagami.interviewtracker.dto.InterviewResponseDTO;
import com.lightyagami.interviewtracker.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
@CrossOrigin(origins = "http://localhost:3000")
public class InterviewController {
	 @Autowired
	    private InterviewService interviewService;
	 
	 @PostMapping
	    public InterviewResponseDTO createInterview(@RequestBody InterviewRequestDTO request) {
	        return interviewService.createInterview(request);
	    }

	    @GetMapping
	    public List<InterviewResponseDTO> getAllInterviews() {
	        return interviewService.getAllInterviews();
	    } 
	    
	    @GetMapping("/by-connection/{connectionId}")
	    public List<InterviewResponseDTO> getByConnection(@PathVariable Long connectionId) {
	        return interviewService.getInterviewsByConnection(connectionId);
	    }

	    @PutMapping("/{id}")
	    public InterviewResponseDTO updateInterview(@PathVariable Long id,
	                                                @RequestBody InterviewRequestDTO request) {
	        return interviewService.updateInterview(id, request);
	    } 
	    
	    @DeleteMapping("/{id}")
	    public ApiResponse deleteInterview(@PathVariable Long id) {
	        interviewService.deleteInterview(id);
	        return new ApiResponse(true, "Interview deleted successfully");
	    }

}
