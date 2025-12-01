package com.lightyagami.interviewtracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lightyagami.interviewtracker.dto.ConnectionRequestDTO;
import com.lightyagami.interviewtracker.dto.ConnectionResponseDTO;
import com.lightyagami.interviewtracker.dto.InterviewResponseDTO;   // <-- ADD THIS
import com.lightyagami.interviewtracker.entity.Connection;
import com.lightyagami.interviewtracker.entity.enums.ConnectionStatus;
import com.lightyagami.interviewtracker.entity.enums.MessageStatus;
import com.lightyagami.interviewtracker.exception.CustomException;
import com.lightyagami.interviewtracker.repository.ConnectionRepository;
import com.lightyagami.interviewtracker.repository.InterviewRepository;
import com.lightyagami.interviewtracker.service.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService {
	 @Autowired
	    private ConnectionRepository connectionRepository;

	    @SuppressWarnings("unused")
		@Autowired
	    private InterviewRepository interviewRepository;
	@Override
	public ConnectionResponseDTO createConnection(ConnectionRequestDTO request) {
		if (connectionRepository.existsByPersonNameAndCompanyAndRole(
                request.getPersonName(), request.getCompany(), request.getRole())) {
            throw new CustomException("Connection already exists with same person, company and role");
        }
		if (request.getPersonName() == null || request.getPersonName().trim().isEmpty()
                || request.getCompany() == null || request.getCompany().trim().isEmpty()
                || request.getRole() == null || request.getRole().trim().isEmpty()) {
            throw new CustomException("PersonName, Company and Role cannot be empty");
        }
		
		 Connection connection = new Connection();
	        connection.setPersonName(request.getPersonName());
	        connection.setCompany(request.getCompany());
	        connection.setRole(request.getRole());
	        connection.setConnectionStatus(ConnectionStatus.valueOf(request.getConnectionStatus()));
	        connection.setMessageStatus(MessageStatus.valueOf(request.getMessageStatus()));
	        connection.setReplyNote(request.getReplyNote());
	        Connection saved = connectionRepository.save(connection);
	        return mapToResponse(saved);
	}

	@Override
	public List<ConnectionResponseDTO> getAllConnections() {
		
		return connectionRepository.findAll().stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public ConnectionResponseDTO getConnectionById(Long id) {
		 Connection connection = connectionRepository.findById(id)
	                .orElseThrow(() -> new CustomException("Connection not found"));
	        return mapToResponse(connection);
	}

	@Override
	public ConnectionResponseDTO updateConnection(Long id, ConnectionRequestDTO request) {
		 Connection connection = connectionRepository.findById(id)
	                .orElseThrow(() -> new CustomException("Connection not found"));

	        ConnectionStatus newStatus = ConnectionStatus.valueOf(request.getConnectionStatus());
	        if (newStatus.ordinal() < connection.getConnectionStatus().ordinal()) {
	            throw new CustomException("Cannot move status backwards");
	        }

	        if (!connection.getInterviews().isEmpty()) {
	            if (!connection.getCompany().equals(request.getCompany())
	                    || !connection.getPersonName().equals(request.getPersonName())
	                    || !connection.getRole().equals(request.getRole())) {
	                throw new CustomException("Cannot modify company, person or role after interview started");
	            }
	        }

	        connection.setConnectionStatus(newStatus);
	        connection.setMessageStatus(MessageStatus.valueOf(request.getMessageStatus()));
	        connection.setReplyNote(request.getReplyNote());

	        return mapToResponse(connectionRepository.save(connection));
	}

	@Override
	public void deleteConnection(Long id) {
		if (!connectionRepository.existsById(id)) {
            throw new CustomException("Connection not found");
        }
        connectionRepository.deleteById(id);
		
	}
	private ConnectionResponseDTO mapToResponse(Connection connection) {
        ConnectionResponseDTO dto = new ConnectionResponseDTO();
        dto.setId(connection.getId());
        dto.setPersonName(connection.getPersonName());
        dto.setCompany(connection.getCompany());
        dto.setRole(connection.getRole());
        dto.setConnectionStatus(connection.getConnectionStatus().name());
        dto.setMessageStatus(connection.getMessageStatus().name());
        dto.setReplyNote(connection.getReplyNote());
        dto.setCreatedAt(connection.getCreatedAt());
        dto.setUpdatedAt(connection.getUpdatedAt());

        dto.setInterviews(connection.getInterviews()
                .stream()
                .map(interview -> {
                    InterviewResponseDTO interviewDTO = new InterviewResponseDTO();
                    interviewDTO.setId(interview.getId());
                    interviewDTO.setRoundNumber(interview.getRoundNumber());
                    interviewDTO.setResult(interview.getResult().name());
                    return interviewDTO;
                }).collect(Collectors.toList()));

        return dto;
    }

}
