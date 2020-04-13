/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tunisiagottalent.services.UserServices;

/**
 *
 * @author alaa
 */
public class sendEmailSMTP {

    public static void sendMail(String username,String recepient,String token) throws Exception {
        System.out.println("Preparing !!");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String email = "tunisiagottalents2020@gmail.com";
        String pwd = "esprit2020";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pwd);
            }

        });
        
        
        
        Message message = prepareMessage(username,session,email,recepient,token);
        
        Transport.send(message);
        
        System.out.println("Success !!");
    }

    private static Message prepareMessage(String username,Session session, String accountEmail,String recepient,String token) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(accountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Password update");
            message.setText("Welcome "+username+"\nYou have asked for a password reset. Please user this code in the reset password window: "+token);
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(sendEmailSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String tokenGenerator(){
        return null;
    }
}
