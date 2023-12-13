package com.iot.back.models.entities.enums;

import com.iot.back.models.services.exceptions.RegisterException;

public enum UserRole {
	MASTER(1),
	ADMIN(2),
	USER(3);

	
	private Integer code;
	private UserRole(Integer code) {
		this.code=code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public static UserRole valueOff(Integer code) {
		for(UserRole value: UserRole.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new RegisterException("Invalid UserRole code");
	}
	
}
