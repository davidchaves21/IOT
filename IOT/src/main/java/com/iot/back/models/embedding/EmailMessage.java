package com.iot.back.models.embedding;

import java.util.ArrayList;
import java.util.List;

public class EmailMessage {

	private String subject;
	private String message;
	private String sender;
	private List<String> recipients = new ArrayList<>();
	
	
	
	public EmailMessage() {
	}

	public EmailMessage(String subject, String message, String sender, List<String> recipients) {
		this.subject = subject;
		this.message = message;
		this.sender = sender;
		this.recipients = recipients;
	}
	
	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public String getSender() {
		return sender;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	

}
