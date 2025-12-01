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
import com.lightyagami.interviewtracker.dto.ConnectionRequestDTO;
import com.lightyagami.interviewtracker.dto.ConnectionResponseDTO;
import com.lightyagami.interviewtracker.service.ConnectionService;

@RestController
@RequestMapping("/api/connections")
@CrossOrigin(origins = "http://localhost:3000")
public class ConnectionController {
	@Autowired
    private ConnectionService connectionService;
	
	 @PostMapping
	    public ConnectionResponseDTO createConnection(@RequestBody ConnectionRequestDTO request) {
	        return connectionService.createConnection(request);
	    }
	 
	 @GetMapping
	    public List<ConnectionResponseDTO> getAllConnections() {
	        return connectionService.getAllConnections();
	    }
	 @GetMapping("/{id}")
	    public ConnectionResponseDTO getConnectionById(@PathVariable Long id) {
	        return connectionService.getConnectionById(id);
	    }
	 
	 @PutMapping("/{id}")
	    public ConnectionResponseDTO updateConnection(@PathVariable Long id,
	                                                  @RequestBody ConnectionRequestDTO request) {
	        return connectionService.updateConnection(id, request);
	    }
	 @DeleteMapping("/{id}")
	    public ApiResponse deleteConnection(@PathVariable Long id) {
	        connectionService.deleteConnection(id);
	        return new ApiResponse(true, "Connection deleted successfully");
	    }
	 
	 
	 
}
