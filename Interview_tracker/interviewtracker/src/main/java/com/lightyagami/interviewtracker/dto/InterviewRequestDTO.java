package com.lightyagami.interviewtracker.dto;

public class InterviewRequestDTO {
	 private Long connectionId;
	    private String interviewerName;
	    private String mode;
	    private String duration;
	    private Integer roundNumber;
	    private String date;
	    private String result;
	    private String notes;

	    public InterviewRequestDTO() {}

		public Long getConnectionId() {
			return connectionId;
		}

		public void setConnectionId(Long connectionId) {
			this.connectionId = connectionId;
		}

		public String getInterviewerName() {
			return interviewerName;
		}
		public Integer getRoundNumber() {
		    return roundNumber;
		}

		public void setRoundNumber(Integer roundNumber) {
		    this.roundNumber = roundNumber;
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

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
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
	    
	    
}
