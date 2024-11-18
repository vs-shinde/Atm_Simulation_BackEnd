package com.hackathon.orangepod.atm.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//	@Autowired
//	private JavaMailSender mailSender;
//
//	public void sendTransactionEmail(String to, String subject, String message) {
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setTo(to);
//		mailMessage.setSubject(subject);
//		mailMessage.setText(message);
//		mailSender.send(mailMessage);
//	}

//}

import org.springframework.stereotype.Service;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
//TODO This service is email sender
@Service
public class EmailService {
	//@Autowired
	//Logger log = org.slf4j.LoggerFactory.getLogger(SendEmailServiceImp.class);

	public boolean sendEmail(String subject, String message, String to, File file)
	{
		try {
			// TODO Email setup configuration..........
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", "smtp.gmail.com");
			properties.setProperty("mail.smtp.port", "587");
			properties.setProperty("mail.smtp.auth", "true");
			properties.setProperty("mail.smtp.starttls.enable", "true");


			Session session = Session.getInstance(properties, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("atmsimulation0@gmail.com", "uctrcemtmfpktiic");
				}

			});
			session.setDebug(true);
			MimeMessage mimeMessage = new MimeMessage(session);
			try{
				mimeMessage.setFrom();
				mimeMessage.setSubject(subject);
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				MimeMultipart mimeMultipart = new MimeMultipart();
				MimeBodyPart mimeFileBody = new MimeBodyPart();
				MimeBodyPart htmlPart = new MimeBodyPart();
				try{

					htmlPart.setContent( message, "text/html; charset=utf-8" );
					// this condintion should be use if file not received
					if(file!=null)
					{
						//TODO file attachment ........
						mimeFileBody.attachFile(file);
						mimeMultipart.addBodyPart(htmlPart);
						mimeMultipart.addBodyPart(mimeFileBody);

					}
					else {
						mimeMultipart.addBodyPart(htmlPart);
					}
				} catch (Exception e) {
					return false;
				}
				mimeMessage.setContent(mimeMultipart );
				Transport.send(mimeMessage);

			}catch(Exception ex){
				return false;
			}

			//   log.info("sendEmail------{}", "sendEmail excuted");
		}catch(Exception e) {
			e.printStackTrace();
			//	log.error("sendEmail------{}", e.getMessage());
			return false;
		}
		return true;
	}


}