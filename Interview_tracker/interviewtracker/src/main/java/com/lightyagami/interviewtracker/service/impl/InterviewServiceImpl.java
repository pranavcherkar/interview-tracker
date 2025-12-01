package com.lightyagami.interviewtracker.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lightyagami.interviewtracker.dto.InterviewRequestDTO;
import com.lightyagami.interviewtracker.dto.InterviewResponseDTO;
import com.lightyagami.interviewtracker.entity.Connection;
import com.lightyagami.interviewtracker.entity.Interview;
import com.lightyagami.interviewtracker.entity.enums.ConnectionStatus;
import com.lightyagami.interviewtracker.entity.enums.InterviewResult;
import com.lightyagami.interviewtracker.exception.CustomException;
import com.lightyagami.interviewtracker.repository.ConnectionRepository;
import com.lightyagami.interviewtracker.repository.InterviewRepository;
import com.lightyagami.interviewtracker.service.InterviewService;

@Service
public class InterviewServiceImpl  implements InterviewService{
	 @Autowired
	    private InterviewRepository interviewRepository;

	    @Autowired
	    private ConnectionRepository connectionRepository;
	@Override
	public InterviewResponseDTO createInterview(InterviewRequestDTO request) {
		 Connection connection = connectionRepository.findById(request.getConnectionId())
	                .orElseThrow(() -> new CustomException("Connection not found"));

	        if (connection.getConnectionStatus() != ConnectionStatus.INTERVIEW_SCHEDULED) {
	            throw new CustomException("Interview not scheduled yet. Set status to INTERVIEW_SCHEDULED first.");
	        }

	        // Check duplicate round number
	        if (interviewRepository.existsByConnectionIdAndRoundNumber(request.getConnectionId(), request.getRoundNumber())) {
	            throw new CustomException("Round already exists for this connection");
	        }

	        // Validate sequential round
	        int lastRound = interviewRepository
	                .findTopByConnectionIdOrderByRoundNumberDesc(request.getConnectionId())
	                .map(Interview::getRoundNumber)
	                .orElse(0);

	        if (request.getRoundNumber() != lastRound + 1) {
	            throw new CustomException("Round must be sequential");
	        }

	        // Check if last round failed -> block next
	        interviewRepository.findTopByConnectionIdOrderByRoundNumberDesc(request.getConnectionId())
	                .ifPresent(last -> {
	                    if (last.getResult() == InterviewResult.FAIL) {
	                        throw new CustomException("Cannot add new round after FAIL result");
	                    }
	                });

	        Interview interview = new Interview();
	        interview.setConnection(connection);
	        interview.setRoundNumber(request.getRoundNumber());
	        interview.setInterviewerName(request.getInterviewerName());
	        interview.setMode(request.getMode());
	        interview.setDuration(request.getDuration());
	        interview.setDate(LocalDate.parse(request.getDate()));
	        interview.setResult(InterviewResult.valueOf(request.getResult()));
	        interview.setNotes(request.getNotes());

	        Interview saved = interviewRepository.save(interview);
	        return mapToResponse(saved);
	}

	@Override
	public List<InterviewResponseDTO> getAllInterviews() {
		 return interviewRepository.findAll().stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	}

	@Override
	public List<InterviewResponseDTO> getInterviewsByConnection(Long connectionId) {
		return interviewRepository.findByConnectionId(connectionId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
	}

	@Override
	public InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO request) {
		 Interview interview = interviewRepository.findById(id)
	                .orElseThrow(() -> new CustomException("Interview not found"));

	        Connection connection = interview.getConnection();

	        if (connection.getConnectionStatus() == ConnectionStatus.OFFER ||
	                connection.getConnectionStatus() == ConnectionStatus.REJECTED) {
	            throw new CustomException("Cannot update interview after final decision");
	        }

	        interview.setInterviewerName(request.getInterviewerName());
	        interview.setMode(request.getMode());
	        interview.setDuration(request.getDuration());
	        interview.setDate(LocalDate.parse(request.getDate()));
	        interview.setNotes(request.getNotes());
	        interview.setResult(InterviewResult.valueOf(request.getResult()));

	        Interview updated = interviewRepository.save(interview);
	        return mapToResponse(updated);
	}

	@Override
	public void deleteInterview(Long id) {
		 Interview interview = interviewRepository.findById(id)
	                .orElseThrow(() -> new CustomException("Interview not found"));

	        Long connectionId = interview.getConnection().getId();
	        int lastRound = interviewRepository
	                .findTopByConnectionIdOrderByRoundNumberDesc(connectionId)
	                .map(Interview::getRoundNumber)
	                .orElse(0);

	        if (interview.getRoundNumber() != lastRound) {
	            throw new CustomException("Only the last round can be deleted");
	        }

	        interviewRepository.deleteById(id);
	}

	 private InterviewResponseDTO mapToResponse(Interview interview) {
	        InterviewResponseDTO dto = new InterviewResponseDTO();
	        dto.setId(interview.getId());
	        dto.setConnectionId(interview.getConnection().getId());
	        dto.setRoundNumber(interview.getRoundNumber());
	        dto.setDate(interview.getDate());
	        dto.setInterviewerName(interview.getInterviewerName());
	        dto.setMode(interview.getMode());
	        dto.setDuration(interview.getDuration());
	        dto.setNotes(interview.getNotes());
	        dto.setCreatedAt(interview.getCreatedAt());
	        dto.setUpdatedAt(interview.getUpdatedAt());
	        dto.setResult(interview.getResult().name());
	        return dto;
	    }
}
