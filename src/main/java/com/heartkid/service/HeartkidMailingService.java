package com.heartkid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Service
public class HeartkidMailingService {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(HeartkidMailingService.class);

    @Value("${mailfromId}")
    public String fromMailId;
    @Value("${mail.smtp.host}")
    public String Host;
    @Value("${mail.smtp.port}")
    public String Port;


    public String mailingservice(String to) {

        System.out.println("MAIL FROM ID----" + fromMailId);
        String response;
        // Assuming you are sending email from localhost
        //String host = "mailhost.qantas.com.au";

        // Get system properties
        Properties props = System.getProperties();
        // Setup mail server
        // properties.setProperty("mail.smtp.host", host);
        props.put("mail.smtp.host", Host);
   /* props.put("mail.smtp.socketFactory.port", Port);
    props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");*/
        //props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", Port);
        // Get the default Session object.
        Session session = Session.getDefaultInstance(props);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromMailId));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Thank you for registration!");

            message.setSentDate(new Date());

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
          /* messageBodyPart = new MimeBodyPart();
	       String filename = "file.txt";
	       DataSource source = new FileDataSource(filename);
	       messageBodyPart.setDataHandler(new DataHandler(source));
	       messageBodyPart.setFileName(filename);
	       multipart.addBodyPart(messageBodyPart);*/

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            response = "success";
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {

            response = "failure";
            LOGGER.info(mex.toString());

        }
        return response;
    }

}
