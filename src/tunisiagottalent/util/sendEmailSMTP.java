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

    public static void RespondToComplaint(String username, String recepient, String Subject, String Response) throws MessagingException {

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
            message.setContent("<!doctype html>\n"
                    + "<html>\n"
                    + "  <head>\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width\" />\n"
                    + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "    <title>Complaint</title>\n"
                    + "    <style>\n"
                    + "\n"
                    + "      body {\n"
                    + "        background-color: #f6f6f6;\n"
                    + "        font-family: sans-serif;\n"
                    + "        -webkit-font-smoothing: antialiased;\n"
                    + "        font-size: 14px;\n"
                    + "        line-height: 1.4;\n"
                    + "        margin: 0;\n"
                    + "        padding: 0;\n"
                    + "        -ms-text-size-adjust: 100%;\n"
                    + "        -webkit-text-size-adjust: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      table {\n"
                    + "        border-collapse: separate;\n"
                    + "        mso-table-lspace: 0pt;\n"
                    + "        mso-table-rspace: 0pt;\n"
                    + "        width: 100%; }\n"
                    + "        table td {\n"
                    + "          font-family: sans-serif;\n"
                    + "          font-size: 14px;\n"
                    + "          vertical-align: top; \n"
                    + "      }\n"
                    + "\n"
                    + "\n"
                    + "      .body {\n"
                    + "        background-color: #f6f6f6;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      .container {\n"
                    + "        display: block;\n"
                    + "        margin: 0 auto !important;\n"
                    + "        /* makes it centered */\n"
                    + "        max-width: 580px;\n"
                    + "        padding: 10px;\n"
                    + "        width: 580px; \n"
                    + "      }\n"
                    + "\n"
                    + "      .content {\n"
                    + "        box-sizing: border-box;\n"
                    + "        display: block;\n"
                    + "        margin: 0 auto;\n"
                    + "        max-width: 580px;\n"
                    + "        padding: 10px; \n"
                    + "      }\n"
                    + "\n"
                    + "      .main {\n"
                    + "        background: #ffffff;\n"
                    + "        border-radius: 3px;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      .wrapper {\n"
                    + "        box-sizing: border-box;\n"
                    + "        padding: 20px; \n"
                    + "      }\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "      .footer {\n"
                    + "        clear: both;\n"
                    + "        margin-top: 10px;\n"
                    + "        text-align: center;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "        .footer td,\n"
                    + "        .footer p,\n"
                    + "        .footer span,\n"
                    + "        .footer a {\n"
                    + "          color: #999999;\n"
                    + "          font-size: 12px;\n"
                    + "          text-align: center; \n"
                    + "      }\n"
                    + "\n"
                    + "    </style>\n"
                    + "  </head>\n"
                    + "  <body class=\"\">\n"
                    + "    <span class=\"preheader\"></span>\n"
                    + "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\n"
                    + "      <tr>\n"
                    + "        <td class=\"container\">\n"
                    + "          <div class=\"content\">\n"
                    + "\n"
                    + "\n"
                    + "            <table role=\"presentation\" class=\"main\">\n"
                    + "\n"
                    + "              <tr>\n"
                    + "                <td class=\"wrapper\">\n"
                    + "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                    <tr>\n"
                    + "                      <td>\n"
                    + "                          <p> <img src=\"https://i.ibb.co/7N9jWXP/logo.png\" style=\"display: block;margin-left: auto;margin-right: auto; width: 50%\"> </p>\n"
                    + "                        <span style=\"font-weight: bold\">Dear </span>\n"
                    + "                        <span style=\"font-weight: bold;color: red\">" + username + " , </span>\n"
                    + "                         \n"
                    + "                        <p>Thank you for taking the time to contact us to explain the issues that have occured recently .</p>\n"
                    + "                        <p>Tunisia's Got Talent regret any inconvenience you have experienced , and our Customer Satisfaction Team is reviewing the information you sent us in order to solve this matter fairly.</p>\n"
                    + "                        "
                    + " "+Subject + " "
                    + "<p>If you need further assistance , you may contact us : </p>\n"
                    + "                           <a href=\"mailto:tunisiagottalents2020@gmail.com?subject=Complain%20from%20"+username+"\">\n"
                    + "                            <img src=\"https://upload.wikimedia.org/wikipedia/commons/archive/4/4e/20160129092800%21Gmail_Icon.png\" height=\"30\" width=\"30\" style=\"border: none;display: inline-block;color: white;\"></a>\n"
                    + "                          <a href=\"https://www.facebook.com/tunisia.got.talents/\" target=\"_blank\"> <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Facebook-icon-1.png/900px-Facebook-icon-1.png\" height=\"30\" width=\"30\"> </a>\n"
                    + "                          \n"
                    + "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\n"
                    + "                          <tbody>\n"
                    + "                            <tr>\n"
                    + "                              <td align=\"left\">\n"
                    + "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                                  <tbody>\n"
                    + "                                  </tbody>\n"
                    + "                                </table>\n"
                    + "                              </td>\n"
                    + "                            </tr>\n"
                    + "                          </tbody>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                    </tr>\n"
                    + "                  </table>\n"
                    + "                </td>\n"
                    + "              </tr>\n"
                    + "\n"
                    + "            </table>\n"
                    + "\n"
                    + "            <div class=\"footer\">\n"
                    + "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                <tr>\n"
                    + "                  <td class=\"content-block\">\n"
                    + "                    <span class=\"apple-link\">2035 Sunset Lake, Residence El Amen,Ariana</span>\n"
                    + "                   \n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                  <td class=\"content-block powered-by\">\n"
                    + "                    Powered by <a href=\"http://htmlemail.io\">KingPins</a>.\n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "              </table>\n"
                    + "            </div>\n"
                    + "\n"
                    + "          </div>\n"
                    + "        </td>\n"
                    + "      </tr>\n"
                    + "    </table>\n"
                    + "  </body>\n"
                    + "</html>", "text/html; charset=utf-8");

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

    public static void SendOrder(String username, String recepient) throws MessagingException, FileNotFoundException, IOException {

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
            message.setSubject("Order");
            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            messageBodyPart.setText("Here's your Order , Thank you for your loyalty");

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Second part is attachment
            messageBodyPart = new MimeBodyPart();

            FileDataSource source = new FileDataSource("order.pdf");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("order.pdf");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

        } catch (MessagingException ex) {
            Logger.getLogger(sendEmailSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transport.send(message);

    }

}
