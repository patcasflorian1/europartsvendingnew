package org.eurovending.utils;

import java.util.*; 
import javax.mail.*; 
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 


public class SendEmaiGmail {
	//send email to admin 
	public static void sendAdminEmail(String messageText) {

		final String username = "eurovendingservice323@gmail.com";
		final String password = "eurovending21782619";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("eurovendingservice323@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("patcasf12@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText(messageText);

			Transport.send(message);

			//System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	//send email to user-recovery password
	public static void senduserEmail(String messageText,String emailUser) {

		final String username = "eurovendingservice323@gmail.com";
		final String password = "eurovending21782619";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("eurovendingservice323@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailUser));
			message.setSubject("New Password");
			message.setText(messageText);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	public boolean checkEmail(String email) {
		boolean isOk = false;
		// String email = "contribute@geeksforgeeks.org"; 
	        if (isValid(email)) {
	        	isOk = true;
	            System.out.print("Yes"); 
	        }
	        else {
	        	isOk = false;
	            System.out.print("No"); 
	            }
	        return isOk;
	        }

}


