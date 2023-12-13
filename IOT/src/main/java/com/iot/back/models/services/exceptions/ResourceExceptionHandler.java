package com.iot.back.models.services.exceptions;

import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.iot.back.config.ControllerResponse.RegisterExceptionCode;
import com.iot.back.models.resources.exceptions.StandardError;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e,
			HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Data base error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	

	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<StandardError> tokenHandler(TokenException e, HttpServletRequest request) {
		String error = "Unauthorized access";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<StandardError> tokenExpiredHandler(TokenExpiredException e, HttpServletRequest request) {
		String error = "Unauthorized access";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(new Date(), status.value(), error,"Token expired",
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(RegisterException.class)
	public ResponseEntity<StandardError> database(RegisterException e, HttpServletRequest request) {
		String error = "Bad request at register";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<StandardError> database(MissingRequestHeaderException e, HttpServletRequest request) {
		String error = "Unauthorized access";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(new Date(), status.value(), error, "Authorization header error",
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StandardError> database(BadCredentialsException e, HttpServletRequest request) {
		String error = "Unauthorized access";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(new Date(), status.value(), error, RegisterExceptionCode.UNAUTHORIZED_REQUEST_LOGIN.toString(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<StandardError> database(IOException e, HttpServletRequest request) {
		String error = "Unauthorized access for FilterChain";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(new Date(), status.value(), error, "IOException",
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(ServletException.class)
	public ResponseEntity<StandardError> database(ServletException e, HttpServletRequest request) {
		String error = "Unauthorized access for FilterChain";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(new Date(), status.value(), error, "ServletException",
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> exception(Exception e, HttpServletRequest request) {
		String error = "Unauthorized access";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<StandardError> messaging(MessagingException e, HttpServletRequest request) {
		String error = "invalid email";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(new Date(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	public ResponseEntity<StandardError> doHandleException(Exception e, HttpServletRequest request) {
		if (e instanceof TokenException) {
			return tokenHandler((TokenException) e, request);
		} else {
			return exception( e, request);
		}
	}
	

}