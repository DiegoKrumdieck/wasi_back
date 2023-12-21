package com.middevs.wasi.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MailService {
	
	@Autowired
    private JavaMailSender emailSender;
	@Async
	public void sendMail(String remitente ,String destinatario,String asunto, String contenido) {
		MimeMessage message = emailSender.createMimeMessage();
		try {
			message.setSubject(asunto);
	        MimeMessageHelper helper;
	        helper = new MimeMessageHelper(message, true);
	        helper.setFrom(remitente);
	        helper.setTo(InternetAddress.parse(destinatario));       
	        helper.setText(contenido, false);
	        emailSender.send(message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
