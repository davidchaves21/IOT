package com.iot.back.models.entities.user;

import io.swagger.v3.oas.annotations.media.Schema;

public class RecoverRequest {
	
	@Schema(description = "User's email address", example = "b13@gmail.com")
	private String email;
	
	

	public RecoverRequest(String email) {
		this.email = email;
	}
	
	
	public RecoverRequest() {
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
