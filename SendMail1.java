package com.sendemail;

import java.util.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail1 {

	public static void main(String[] args) {

		// Recipient's email ID needs to be mentioned.
		String to = "masonfout@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "Maserati.Sec@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();
		sound();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("Maserati.Sec@gmail.com", "maserati370");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Stop you violated the law");

			// Now set the actual message
			message.setText("pay the court a fine\n"
					+ "file:///C:/xampp/htdocs/Assignment.html");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	
	public static void sound() {
		try {

			FileInputStream fileInputStream = new FileInputStream("stop.mp3");
			Player player = new Player(fileInputStream);
			player.play();
			System.out.println("Song is playing");
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (JavaLayerException e) {
			e.printStackTrace();
		}

	}
}




