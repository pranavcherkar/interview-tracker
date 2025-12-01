package com.lightyagami.interviewtracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InterviewResponseDTO {
	 private Long id;
	    private Long connectionId;
	    private Integer roundNumber;
	    private String interviewerName;
	    private String mode;
	    private String duration;
	    private LocalDate date;
	    private String result;
	    private String notes;
	    private LocalDate createdAt;
	    private LocalDateTime updatedAt;

	    public InterviewResponseDTO() {}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getConnectionId() {
			return connectionId;
		}

		public void setConnectionId(Long connectionId) {
			this.connectionId = connectionId;
		}

		public Integer getRoundNumber() {
			return roundNumber;
		}

		public void setRoundNumber(Integer roundNumber) {
			this.roundNumber = roundNumber;
		}

		public String getInterviewerName() {
			return interviewerName;
		}

		public void setInterviewerName(String interviewerName) {
			this.interviewerName = interviewerName;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
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
	    
}
