/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.util;

import com.itextpdf.text.Document;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author alaa
 */
public class sendEmailSMTP {

    public static Properties config() {
        System.out.println("Preparing !!");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("email", "tunisiagottalents2020@gmail.com");
        properties.put("pwd", "esprit2020");

        return properties;
    }

    public static void changePasswordEmail(String username, String recepient, String token) throws MessagingException {

        Session session = Session.getInstance(config(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config().getProperty("email"), config().getProperty("pwd"));
            }

        });

        Message message = new MimeMessage(session);
        try {

            message.setFrom(new InternetAddress(config().getProperty("email")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Password update");
            message.setText("Welcome " + username + "\nYou have asked for a password reset. Please user this code in the reset password window: " + token);

        } catch (MessagingException ex) {
            Logger.getLogger(sendEmailSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transport.send(message);

        System.out.println("Success !!");
    }
    
    public static void PromoteUser(String username, String recepient) throws MessagingException {

        Session session = Session.getInstance(config(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config().getProperty("email"), config().getProperty("pwd"));
            }

        });

        Message message = new MimeMessage(session);
        try {

            message.setFrom(new InternetAddress(config().getProperty("email")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Promoted To Talented");
            

            BodyPart messageBodyPart = new MimeBodyPart();
           // Fill the message
         messageBodyPart.setText("Congrats " + username + " You Have Won a Competition And Became An Elite User on our Platform");
         
         // Create a multipart message for attachment
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Second part is attachment
         messageBodyPart = new MimeBodyPart();
         
        
         FileDataSource source = new FileDataSource("congrats.jpeg");
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName("congrats.jpeg");
         multipart.addBodyPart(messageBodyPart);
            
         // Send the complete message parts
        
            message.setContent(multipart);
            
            

            
            
            
        } catch (MessagingException ex) {
            Logger.getLogger(sendEmailSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transport.send(message);

        System.out.println("Success !!");
    }
     public static void RespondToComplaint(String username, String recepient,String Subject,String Response) throws MessagingException {

        Session session = Session.getInstance(config(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config().getProperty("email"), config().getProperty("pwd"));
            }

        });

        Message message = new MimeMessage(session);
        try {

            message.setFrom(new InternetAddress(config().getProperty("email")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Complaint");
            message.setText("Hello " + username + " \n Thank you for your feedback\n"+ "Subject: "+Subject+"\nResponse: "+Response);

        } catch (MessagingException ex) {
            Logger.getLogger(sendEmailSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transport.send(message);

        System.out.println("Success !!");
    }
     public static void SendTicket(String username, String recepient) throws MessagingException, FileNotFoundException, IOException {

        Session session = Session.getInstance(config(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config().getProperty("email"), config().getProperty("pwd"));
            }

        });

        Message message = new MimeMessage(session);
        try {

            message.setFrom(new InternetAddress(config().getProperty("email")));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Ticket");
            // Create the message body part
         BodyPart messageBodyPart = new MimeBodyPart();
           // Fill the message
         messageBodyPart.setText("Here's your ticket");
         
         // Create a multipart message for attachment
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Second part is attachment
         messageBodyPart = new MimeBodyPart();
         
        
         FileDataSource source = new FileDataSource("test.pdf");
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName("Ticket.pdf");
         multipart.addBodyPart(messageBodyPart);
            
         // Send the complete message parts
        
            message.setContent(multipart);
            
            

        } catch (MessagingException ex) {
            Logger.getLogger(sendEmailSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transport.send(message);

        System.out.println("Success !!");
    }

}
