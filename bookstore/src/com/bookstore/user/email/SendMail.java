package com.bookstore.user.email;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.utils.CommonUtils;

public class SendMail {
	public void sendMail(String to) {
		Properties p = new Properties();
		try {
			p.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(p.getProperty("username"), p.getProperty("password"));
			}	
		};
		
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg= new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(p.getProperty("from")));
			msg.setRecipients(RecipientType.TO,to);
			msg.setSubject(p.getProperty("subject"));
			String content = p.getProperty("content");
			content = MessageFormat.format(content,CommonUtils.uuid()+"&email="+to);
			msg.setContent(content,"text/html;charset=utf-8");
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
