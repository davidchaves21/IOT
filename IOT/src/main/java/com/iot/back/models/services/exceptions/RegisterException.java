package com.iot.back.models.services.exceptions;

public class RegisterException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RegisterException(String e) {
		super (e);
	}

}
