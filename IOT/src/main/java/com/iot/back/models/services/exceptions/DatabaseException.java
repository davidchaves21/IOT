package com.iot.back.models.services.exceptions;

public class DatabaseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseException (String e ) {
		super(e);
	}
}
