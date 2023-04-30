package com.avisys.cim.payloads;

// This is the Basic Template for output purpose
public class ApiResponse {
	private String message;
	private boolean status;
	
	public ApiResponse() {
		super();
	}

	// To instantiate the Class Fields
	public ApiResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}

	// Getters and Setters are required for Encapsulation as we have kept fields as private members.
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
}
