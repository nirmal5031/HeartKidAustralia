package com.heartkid.service;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.heartkid.controller.HeartkidController;

@Service
public class HeartkidMailingService {
	private static final Logger LOGGER = LoggerFactory
            .getLogger(HeartkidMailingService.class);
	
	
	public String mailingservice()
	{
		String response;
	// Recipient's email ID needs to be mentioned.
    String to = "nirmal5031@gmail.com";

    // Sender's email ID needs to be mentioned
    String from = "nirmal.k1@tcs.com";

    // Assuming you are sending email from localhost
    String host = "mailhost.qantas.com.au";

    // Get system properties
    Properties props = System.getProperties();
    // Setup mail server
   // properties.setProperty("mail.smtp.host", host);
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.socketFactory.port", "8080");
    props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "8080");
    // Get the default Session object.
    Session session = Session.getDefaultInstance(props);

    try{
       // Create a default MimeMessage object.
       MimeMessage message = new MimeMessage(session);

       // Set From: header field of the header.
       message.setFrom(new InternetAddress(from));

       // Set To: header field of the header.
       message.addRecipient(Message.RecipientType.TO,
                                new InternetAddress(to));

       // Set Subject: header field
       message.setSubject("This is the Subject Line!");

       // Create the message part 
       BodyPart messageBodyPart = new MimeBodyPart();

       // Fill the message
       messageBodyPart.setText("This is message body");
       
       // Create a multipar message
       Multipart multipart = new MimeMultipart();

       // Set text message part
       multipart.addBodyPart(messageBodyPart);

       // Part two is attachment
       messageBodyPart = new MimeBodyPart();
       String filename = "file.txt";
       DataSource source = new FileDataSource(filename);
       messageBodyPart.setDataHandler(new DataHandler(source));
       messageBodyPart.setFileName(filename);
       multipart.addBodyPart(messageBodyPart);

       // Send the complete message parts
       message.setContent(multipart );

       // Send message
       Transport.send(message);
       response = "success";
       System.out.println("Sent message successfully....");
    }
    catch (MessagingException mex) {
     
    	response="failure";
       LOGGER.info(mex.toString());
      
    }
	return response;
	}

}
