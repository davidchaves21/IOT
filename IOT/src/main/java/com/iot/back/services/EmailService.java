package com.iot.back.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iot.back.models.embedding.EmailMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	String emailB13;
	
	//metodo para enviar msg
	public void send(EmailMessage emailMenssage)  throws MessagingException{
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		helper.setFrom(emailB13);
		helper.setSubject(emailMenssage.getSubject());
		//permiter enviar msg em html
		helper.setText(emailMenssage.getMessage(),true);
		//array de string dos destinatarios
		helper.setBcc(emailMenssage.getRecipients().toArray(new  String[emailMenssage.getRecipients().size()]));
		
		javaMailSender.send(mimeMessage);
	}
	
}
