package com.iot.back.models.services.exceptions;

import java.io.IOException;
import java.time.Instant;

import javax.servlet.ServletException;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.TokenExpiredException;

public class TokenException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenException(String message) {
        super(message);
    }

	


}
