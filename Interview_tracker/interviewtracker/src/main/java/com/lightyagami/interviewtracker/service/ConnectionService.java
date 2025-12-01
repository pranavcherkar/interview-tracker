package com.lightyagami.interviewtracker.service;

import java.util.List;

import com.lightyagami.interviewtracker.dto.ConnectionRequestDTO;
import com.lightyagami.interviewtracker.dto.ConnectionResponseDTO;

public interface ConnectionService {

	 ConnectionResponseDTO createConnection(ConnectionRequestDTO request);
	 List<ConnectionResponseDTO> getAllConnections();
	 
	 ConnectionResponseDTO getConnectionById(Long id);
	 
	 ConnectionResponseDTO updateConnection(Long id, ConnectionRequestDTO request);
	 
	 void deleteConnection(Long id);
}
