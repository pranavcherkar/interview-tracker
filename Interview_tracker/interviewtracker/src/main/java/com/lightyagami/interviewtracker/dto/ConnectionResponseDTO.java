package com.lightyagami.interviewtracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ConnectionResponseDTO {
	 private Long id;
	    private String personName;
	    private String company;
	    private String role;
	    private String connectionStatus;
	    private String messageStatus;
	    private String replyNote;
	    private LocalDate createdAt;
	    private LocalDateTime updatedAt;
	    private List<InterviewResponseDTO> interviews;

	    public ConnectionResponseDTO() {}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPersonName() {
			return personName;
		}

		public void setPersonName(String personName) {
			this.personName = personName;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getConnectionStatus() {
			return connectionStatus;
		}

		public void setConnectionStatus(String connectionStatus) {
			this.connectionStatus = connectionStatus;
		}

		public String getMessageStatus() {
			return messageStatus;
		}

		public void setMessageStatus(String messageStatus) {
			this.messageStatus = messageStatus;
		}

		public String getReplyNote() {
			return replyNote;
		}

		public void setReplyNote(String replyNote) {
			this.replyNote = replyNote;
		}

		public LocalDate getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDate createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}

		public List<InterviewResponseDTO> getInterviews() {
			return interviews;
		}

		public void setInterviews(List<InterviewResponseDTO> interviews) {
			this.interviews = interviews;
		}
	    
	    
}
