package com.iot.back.models.entities.user;

public class PutRegisterRequest {
	private String image;
	
	public PutRegisterRequest(String image) {
		this.image = image;
	}

	public PutRegisterRequest() {
		this.image = "";
	}

	

	public String getImage() {
		return image;
	}

	
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
