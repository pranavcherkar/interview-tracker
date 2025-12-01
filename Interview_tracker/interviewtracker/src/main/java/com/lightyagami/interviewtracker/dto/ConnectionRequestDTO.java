package com.lightyagami.interviewtracker.dto;

public class ConnectionRequestDTO {
	 private String personName;
	    private String company;
	    private String role;
	    private String connectionStatus;
	    private String messageStatus;
	    private String replyNote;

	    public ConnectionRequestDTO() {}

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
	    
}
