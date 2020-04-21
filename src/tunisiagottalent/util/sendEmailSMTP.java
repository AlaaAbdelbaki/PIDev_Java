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
            message.setSubject("Congrats!");
            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            messageBodyPart.setText("You Won A competition , You can post as many videos as you want!");

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Second part is attachment
            messageBodyPart = new MimeBodyPart();

            FileDataSource source = new FileDataSource("congrats.jpg");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("congrats.jpg");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

        } catch (MessagingException ex) {
            Logger.getLogger(sendEmailSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transport.send(message);

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
                    + " " + Response + " "
                    + "<p>If you need further assistance , you may contact us : </p>\n"
                    + "                           <a href=\"mailto:tunisiagottalents2020@gmail.com?subject=Complain%20from%20" + username + "\">\n"
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

    public static void SignUp(String username, String recepient, int id) throws MessagingException {

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
            message.setSubject("Confirmation Email! Welcome");
            message.setContent("<!DOCTYPE html>\n"
                    + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width\"> \n"
                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> \n"
                    + "    <meta name=\"x-apple-disable-message-reformatting\"> -->\n"
                    + "    <title></title> \n"
                    + "\n"
                    + "    <link href=\"https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,600,700,800,900\" rel=\"stylesheet\">  \n"
                    + "\n"
                    + "    <style>\n"
                    + "\n"
                    + "        html,\n"
                    + "body {\n"
                    + "    margin: 0 auto !important;\n"
                    + "    padding: 0 !important;\n"
                    + "    height: 100% !important;\n"
                    + "    width: 100% !important;\n"
                    + "    background: #f1f1f1;\n"
                    + "}\n"
                    + "\n"
                    + "* {\n"
                    + "    -ms-text-size-adjust: 100%;\n"
                    + "    -webkit-text-size-adjust: 100%;\n"
                    + "}\n"
                    + "\n"
                    + "div[style*=\"margin: 16px 0\"] {\n"
                    + "    margin: 0 !important;\n"
                    + "}\n"
                    + "\n"
                    + "table,\n"
                    + "td {\n"
                    + "    mso-table-lspace: 0pt !important;\n"
                    + "    mso-table-rspace: 0pt !important;\n"
                    + "}\n"
                    + "\n"
                    + "table {\n"
                    + "    border-spacing: 0 !important;\n"
                    + "    border-collapse: collapse !important;\n"
                    + "    table-layout: fixed !important;\n"
                    + "    margin: 0 auto !important;\n"
                    + "}\n"
                    + "\n"
                    + "img {\n"
                    + "    -ms-interpolation-mode:bicubic;\n"
                    + "}\n"
                    + "\n"
                    + "a {\n"
                    + "    text-decoration: none;\n"
                    + "}\n"
                    + "\n"
                    + "*[x-apple-data-detectors], \n"
                    + ".unstyle-auto-detected-links *,\n"
                    + ".aBn {\n"
                    + "    border-bottom: 0 !important;\n"
                    + "    cursor: default !important;\n"
                    + "    color: inherit !important;\n"
                    + "    text-decoration: none !important;\n"
                    + "    font-size: inherit !important;\n"
                    + "    font-family: inherit !important;\n"
                    + "    font-weight: inherit !important;\n"
                    + "    line-height: inherit !important;\n"
                    + "}\n"
                    + "\n"
                    + ".a6S {\n"
                    + "    display: none !important;\n"
                    + "    opacity: 0.01 !important;\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + ".im {\n"
                    + "    color: inherit !important;\n"
                    + "}\n"
                    + "\n"
                    + "img.g-img + div {\n"
                    + "    display: none !important;\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + "@media only screen and (min-device-width: 320px) and (max-device-width: 374px) {\n"
                    + "    u ~ div .email-container {\n"
                    + "        min-width: 320px !important;\n"
                    + "    }\n"
                    + "}\n"
                    + "\n"
                    + "@media only screen and (min-device-width: 375px) and (max-device-width: 413px) {\n"
                    + "    u ~ div .email-container {\n"
                    + "        min-width: 375px !important;\n"
                    + "    }\n"
                    + "}\n"
                    + "\n"
                    + "@media only screen and (min-device-width: 414px) {\n"
                    + "    u ~ div .email-container {\n"
                    + "        min-width: 414px !important;\n"
                    + "    }\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + "    </style>\n"
                    + "\n"
                    + "    <style>\n"
                    + "\n"
                    + "	    .primary{\n"
                    + "	background: #f5564e;\n"
                    + "}\n"
                    + ".bg_white{\n"
                    + "	background: #ffffff;\n"
                    + "}\n"
                    + ".bg_light{\n"
                    + "	background: #fafafa;\n"
                    + "}\n"
                    + ".bg_black{\n"
                    + "	background: #000000;\n"
                    + "}\n"
                    + ".bg_dark{\n"
                    + "	background: rgba(0,0,0,.8);\n"
                    + "}\n"
                    + ".email-section{\n"
                    + "	padding:2.5em;\n"
                    + "}\n"
                    + "\n"
                    + ".btn{\n"
                    + "	padding: 5px 15px;\n"
                    + "	display: inline-block;\n"
                    + "}\n"
                    + ".btn.btn-primary{\n"
                    + "	border-radius: 5px;\n"
                    + "	background: #f5564e;\n"
                    + "	color: #ffffff;\n"
                    + "}\n"
                    + ".btn.btn-white{\n"
                    + "	border-radius: 5px;\n"
                    + "	background: #ffffff;\n"
                    + "	color: #000000;\n"
                    + "}\n"
                    + ".btn.btn-white-outline{\n"
                    + "	border-radius: 5px;\n"
                    + "	background: transparent;\n"
                    + "	border: 1px solid #fff;\n"
                    + "	color: #fff;\n"
                    + "}\n"
                    + "\n"
                    + "h1,h2,h3,h4,h5,h6{\n"
                    + "	font-family: 'Nunito Sans', sans-serif;\n"
                    + "	color: #000000;\n"
                    + "	margin-top: 0;\n"
                    + "}\n"
                    + "\n"
                    + "body{\n"
                    + "	font-family: 'Nunito Sans', sans-serif;\n"
                    + "	font-weight: 400;\n"
                    + "	font-size: 15px;\n"
                    + "	line-height: 1.8;\n"
                    + "	color: rgba(0,0,0,.4);\n"
                    + "}\n"
                    + "\n"
                    + "a{\n"
                    + "	color: #f5564e;\n"
                    + "}\n"
                    + "\n"
                    + "table{\n"
                    + "}\n"
                    + "\n"
                    + ".logo h1{\n"
                    + "	margin: 0;\n"
                    + "}\n"
                    + ".logo h1 a{\n"
                    + "	color: #000;\n"
                    + "	font-size: 20px;\n"
                    + "	font-weight: 700;\n"
                    + "	text-transform: uppercase;\n"
                    + "	font-family: 'Nunito Sans', sans-serif;\n"
                    + "}\n"
                    + "\n"
                    + ".navigation{\n"
                    + "	padding: 0;\n"
                    + "}\n"
                    + ".navigation li{\n"
                    + "	list-style: none;\n"
                    + "	display: inline-block;;\n"
                    + "	margin-left: 5px;\n"
                    + "	font-size: 12px;\n"
                    + "	font-weight: 700;\n"
                    + "	text-transform: uppercase;\n"
                    + "}\n"
                    + ".navigation li a{\n"
                    + "	color: rgba(0,0,0,.6);\n"
                    + "}\n"
                    + "\n"
                    + "/*HERO*/\n"
                    + ".hero{\n"
                    + "	position: relative;\n"
                    + "	z-index: 0;\n"
                    + "}\n"
                    + ".hero .overlay{\n"
                    + "	position: absolute;\n"
                    + "	top: 0;\n"
                    + "	left: 0;\n"
                    + "	right: 0;\n"
                    + "	bottom: 0;\n"
                    + "	content: '';\n"
                    + "	width: 100%;\n"
                    + "	background: #000000;\n"
                    + "	z-index: -1;\n"
                    + "	opacity: .3;\n"
                    + "}\n"
                    + ".hero .icon{\n"
                    + "}\n"
                    + ".hero .icon a{\n"
                    + "	display: block;\n"
                    + "	width: 60px;\n"
                    + "	margin: 0 auto;\n"
                    + "}\n"
                    + ".hero .text{\n"
                    + "	color: rgba(255,255,255,.8);\n"
                    + "	padding: 0 4em;\n"
                    + "}\n"
                    + ".hero .text h2{\n"
                    + "	color: #ffffff;\n"
                    + "	font-size: 40px;\n"
                    + "	margin-bottom: 0;\n"
                    + "	line-height: 1.2;\n"
                    + "	font-weight: 900;\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + ".heading-section{\n"
                    + "}\n"
                    + ".heading-section h2{\n"
                    + "	color: #000000;\n"
                    + "	font-size: 24px;\n"
                    + "	margin-top: 0;\n"
                    + "	line-height: 1.4;\n"
                    + "	font-weight: 700;\n"
                    + "}\n"
                    + ".heading-section .subheading{\n"
                    + "	margin-bottom: 20px !important;\n"
                    + "	display: inline-block;\n"
                    + "	font-size: 13px;\n"
                    + "	text-transform: uppercase;\n"
                    + "	letter-spacing: 2px;\n"
                    + "	color: rgba(0,0,0,.4);\n"
                    + "	position: relative;\n"
                    + "}\n"
                    + ".heading-section .subheading::after{\n"
                    + "	position: absolute;\n"
                    + "	left: 0;\n"
                    + "	right: 0;\n"
                    + "	bottom: -10px;\n"
                    + "	content: '';\n"
                    + "	width: 100%;\n"
                    + "	height: 2px;\n"
                    + "	background: #f5564e;\n"
                    + "	margin: 0 auto;\n"
                    + "}\n"
                    + "\n"
                    + ".heading-section-white{\n"
                    + "	color: rgba(255,255,255,.8);\n"
                    + "}\n"
                    + ".heading-section-white h2{\n"
                    + "	font-family: \n"
                    + "	line-height: 1;\n"
                    + "	padding-bottom: 0;\n"
                    + "}\n"
                    + ".heading-section-white h2{\n"
                    + "	color: #ffffff;\n"
                    + "}\n"
                    + ".heading-section-white .subheading{\n"
                    + "	margin-bottom: 0;\n"
                    + "	display: inline-block;\n"
                    + "	font-size: 13px;\n"
                    + "	text-transform: uppercase;\n"
                    + "	letter-spacing: 2px;\n"
                    + "	color: rgba(255,255,255,.4);\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + ".icon{\n"
                    + "	text-align: center;\n"
                    + "}\n"
                    + ".icon img{\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + ".services{\n"
                    + "	background: rgba(0,0,0,.03);\n"
                    + "}\n"
                    + ".text-services{\n"
                    + "	padding: 10px 10px 0; \n"
                    + "	text-align: center;\n"
                    + "}\n"
                    + ".text-services h3{\n"
                    + "	font-size: 16px;\n"
                    + "	font-weight: 600;\n"
                    + "}\n"
                    + "\n"
                    + ".services-list{\n"
                    + "	padding: 0;\n"
                    + "	margin: 0 0 10px 0;\n"
                    + "	width: 100%;\n"
                    + "	float: left;\n"
                    + "}\n"
                    + "\n"
                    + ".services-list .text{\n"
                    + "	width: 100%;\n"
                    + "	float: right;\n"
                    + "}\n"
                    + ".services-list h3{\n"
                    + "	margin-top: 0;\n"
                    + "	margin-bottom: 0;\n"
                    + "	font-size: 18px;\n"
                    + "}\n"
                    + ".services-list p{\n"
                    + "	margin: 0;\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + ".text-tour{\n"
                    + "	padding-top: 10px;\n"
                    + "}\n"
                    + ".text-tour h3{\n"
                    + "	margin-bottom: 0;\n"
                    + "}\n"
                    + ".text-tour h3 a{\n"
                    + "	color: #000;\n"
                    + "}\n"
                    + "\n"
                    + ".text-services .meta{\n"
                    + "	text-transform: uppercase;\n"
                    + "	font-size: 14px;\n"
                    + "}\n"
                    + "\n"
                    + ".text-testimony .name{\n"
                    + "	margin: 0;\n"
                    + "}\n"
                    + ".text-testimony .position{\n"
                    + "	color: rgba(0,0,0,.3);\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + ".counter{\n"
                    + "	width: 100%;\n"
                    + "	position: relative;\n"
                    + "	z-index: 0;\n"
                    + "}\n"
                    + ".counter .overlay{\n"
                    + "	position: absolute;\n"
                    + "	top: 0;\n"
                    + "	left: 0;\n"
                    + "	right: 0;\n"
                    + "	bottom: 0;\n"
                    + "	content: '';\n"
                    + "	width: 100%;\n"
                    + "	background: #000000;\n"
                    + "	z-index: -1;\n"
                    + "	opacity: .3;\n"
                    + "}\n"
                    + ".counter-text{\n"
                    + "	text-align: center;\n"
                    + "}\n"
                    + ".counter-text .num{\n"
                    + "	display: block;\n"
                    + "	color: #ffffff;\n"
                    + "	font-size: 34px;\n"
                    + "	font-weight: 700;\n"
                    + "}\n"
                    + ".counter-text .name{\n"
                    + "	display: block;\n"
                    + "	color: rgba(255,255,255,.9);\n"
                    + "	font-size: 13px;\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + "ul.social{\n"
                    + "	padding: 0;\n"
                    + "}\n"
                    + "ul.social li{\n"
                    + "	display: inline-block;\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + ".footer{\n"
                    + "	color: rgba(255,255,255,.5);\n"
                    + "\n"
                    + "}\n"
                    + ".footer .heading{\n"
                    + "	color: #ffffff;\n"
                    + "	font-size: 20px;\n"
                    + "}\n"
                    + ".footer ul{\n"
                    + "	margin: 0;\n"
                    + "	padding: 0;\n"
                    + "}\n"
                    + ".footer ul li{\n"
                    + "	list-style: none;\n"
                    + "	margin-bottom: 10px;\n"
                    + "}\n"
                    + ".footer ul li a{\n"
                    + "	color: rgba(255,255,255,1);\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + "@media screen and (max-width: 500px) {\n"
                    + "\n"
                    + "	.icon{\n"
                    + "		text-align: left;\n"
                    + "	}\n"
                    + "\n"
                    + "	.text-services{\n"
                    + "		padding-left: 0;\n"
                    + "		padding-right: 20px;\n"
                    + "		text-align: left;\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + "    </style>\n"
                    + "\n"
                    + "\n"
                    + "</head>\n"
                    + "\n"
                    + "<body width=\"100%\" style=\"margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #222222;\">\n"
                    + "	<center style=\"width: 100%; background-color: #f1f1f1;\">\n"
                    + "    <div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\n"
                    + "      &zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;\n"
                    + "    </div>\n"
                    + "    <div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">\n"
                    + "    	<!-- BEGIN BODY -->\n"
                    + "      <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"margin: auto;\">\n"
                    + "      	<tr>\n"
                    + "          <td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em;\">\n"
                    + "          	<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                    + "          		<tr>\n"
                    + "          			<td width=\"60%\" class=\"logo\" style=\"text-align: left;\">\n"
                    + "			            <img src=\"https://i.ibb.co/BgDfqxz/logo.png\" height=\"70\" width=\"200\">\n"
                    + "			          </td>\n"
                    + "			          <td width=\"40%\" class=\"logo\" style=\"text-align: right;\">\n"
                    + "			            <ul class=\"navigation\">\n"
                    + "			            	<li><a href=\"http://127.0.0.1:8000/\">Home</a></li>\n"
                    + "			            	<li><a href=\"http://127.0.0.1:8000/\">About</a></li>\n"
                    + "			            	<li><a href=\"http://127.0.0.1:8000/\">Blog</a></li>\n"
                    + "			            	<li><a href=\"http://127.0.0.1:8000/\">Contact</a></li>\n"
                    + "			            </ul>\n"
                    + "			          </td>\n"
                    + "          		</tr>\n"
                    + "          	</table>\n"
                    + "          </td>\n"
                    + "	      </tr>\n"
                    + "				<tr>\n"
                    + "          <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url(https://i.ibb.co/6Pb6Yy2/bg.jpg); background-size: cover; height: 400px;\">\n"
                    + "          	<div class=\"overlay\"></div>\n"
                    + "            <table>\n"
                    + "            	<tr>\n"
                    + "            		<td>\n"
                    + "            			<div class=\"text\" style=\"text-align: center;\">\n"
                    + "            				<h2>Share your talent,</h2>\n"
                    + "                            <h2>Move the world.</h2>\n"
                    + "            			</div>\n"
                    + "            		</td>\n"
                    + "            	</tr>\n"
                    + "            </table>\n"
                    + "          </td>\n"
                    + "	      </tr>\n"
                    + "	      <tr>\n"
                    + "	        <td class=\"bg_dark email-section\" style=\"text-align:center;\">\n"
                    + "	        	<div class=\"heading-section heading-section-white\">\n"
                    + "	          	<h2>Welcome To Tunisia's Got Talent</h2>\n"
                    + "	          	<p>our application attracts a variety of participants, from across Tunisia   and abroad, to take part and who possess some form of talents, with acts ranging from singing, dancing, comedy, magic, stunts, variety, and other genres</p>\n"
                    + "                <p> <button style=\"background-color: black;border: none;color: white;padding: 15px 32px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;\"><a target=\"_blank\" href=\"http://127.0.0.1:8000/\">Update Your Profile</a></button> </p>\n"
                    + "	        	</div>\n"
                    + "	        </td>\n"
                    + "	      </tr>\n"
                    + "	      <tr>\n"
                    + "		      <td class=\"bg_white\">\n"
                    + "		        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "		        	<tr>\n"
                    + "					      <td class=\"bg_white\">\n"
                    + "					        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "					          <tr>\n"
                    + "					            <td class=\"bg_white email-section\">\n"
                    + "					            	<div class=\"heading-section\" style=\"text-align: center; padding: 0 30px;\">\n"
                    + "					              	<h2>Best Events</h2>\n"
                    + "					            	</div>\n"
                    + "					            	<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                    + "					            		<tr>\n"
                    + "			                      <td valign=\"top\" width=\"50%\">\n"
                    + "			                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "			                          <tr>\n"
                    + "			                            <td style=\"padding-top: 20px; padding-right: 10px;\">\n"
                    + "			                              <a href=\"#\"><img src=\"https://i.ibb.co/jGYK7g4/2.jpg\" alt=\"\" style=\"width: 100%; max-width: 600px; height: auto; margin: auto; display: block;\"></a>\n"
                    + "			                              <div class=\"text-tour\" style=\"text-align: center;\">\n"
                    + "			                              	<h3><a href=\"#\">Bob Dylan</a></h3>\n"
                    + "			                              	<span class=\"price\">8/20/2020</span>\n"
                    + "			                              </div>\n"
                    + "			                            </td>\n"
                    + "			                          </tr>\n"
                    + "			                          <tr>\n"
                    + "			                            <td style=\"padding-top: 20px; padding-right: 10px;\">\n"
                    + "			                              <a href=\"#\"><img src=\"https://i.ibb.co/3BbS6BP/7.jpg\" alt=\"\" style=\"width: 100%; max-width: 600px; height: auto; margin: auto; display: block;\"></a>\n"
                    + "			                              <div class=\"text-tour\" style=\"text-align: center;\">\n"
                    + "			                              	<h3><a href=\"#\">Johnny Cash</a></h3>\n"
                    + "			                              	<span class=\"price\">8/24/2020</span>\n"
                    + "			                              </div>\n"
                    + "			                            </td>\n"
                    + "			                          </tr>\n"
                    + "			                          \n"
                    + "			                        </table>\n"
                    + "			                      </td>\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "			                      <td valign=\"top\" width=\"50%\">\n"
                    + "			                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "			                          <tr>\n"
                    + "			                            <td style=\"padding-top: 20px; padding-left: 10px;\">\n"
                    + "			                              <a href=\"#\"><img src=\"https://i.ibb.co/XFgY4xB/11.jpg\" alt=\"\" style=\"width: 100%; max-width: 600px; height: auto; margin: auto; display: block;\"></a>\n"
                    + "			                              <div class=\"text-tour\" style=\"text-align: center;\">\n"
                    + "			                              	<h3><a href=\"#\">PewDiePie</a></h3>\n"
                    + "			                              	<span class=\"price\">7/7/2020</span>\n"
                    + "			                              </div>\n"
                    + "			                            </td>\n"
                    + "			                          </tr>\n"
                    + "			                          <tr>\n"
                    + "			                            <td style=\"padding-top: 20px; padding-left: 10px;\">\n"
                    + "			                              <a href=\"#\"><img src=\"https://i.ibb.co/7y4fZNZ/17.jpg\" alt=\"\" style=\"width: 100%; max-width: 600px; height: auto; margin: auto; display: block;\"></a>\n"
                    + "			                              <div class=\"text-tour\" style=\"text-align: center;\">\n"
                    + "			                              	<h3><a href=\"#\">Pink Guy</a></h3>\n"
                    + "			                              	<span class=\"price\">11/11/2020</span>\n"
                    + "			                              </div>\n"
                    + "			                            </td>\n"
                    + "			                          </tr>\n"
                    + "			                          \n"
                    + "			                        </table>\n"
                    + "			                      </td>\n"
                    + "			                    </tr>\n"
                    + "					            	</table>\n"
                    + "					            </td>\n"
                    + "					          </tr>\n"
                    + "\n"
                    + "					        </table>\n"
                    + "\n"
                    + "					      </td>\n"
                    + "					    </tr>\n"
                    + "		          <tr>\n"
                    + "			          <td valign=\"middle\" class=\"counter\" style=\"background-image: url(https://i.ibb.co/pxxRSMS/5.jpg); background-size: cover; padding: 4em 0;\">\n"
                    + "			          	<div class=\"overlay\"></div>\n"
                    + "			            <table>\n"
                    + "			            	<tr>\n"
                    + "			            		<td valign=\"middle\" width=\"33.333%\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"counter-text\">\n"
                    + "                            	<span class=\"num\">200</span>\n"
                    + "                            	<span class=\"name\">Amazing Talented</span>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                      <td valign=\"middle\" width=\"33.333%\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"counter-text\">\n"
                    + "                            	<span class=\"num\">12000</span>\n"
                    + "                            	<span class=\"name\">Sold Tickets</span>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                      <td valign=\"middle\" width=\"33.333%\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"counter-text\">\n"
                    + "                            	<span class=\"num\">1000</span>\n"
                    + "                            	<span class=\"name\">Happy Users</span>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "			            	</tr>\n"
                    + "			            </table>\n"
                    + "			          </td>\n"
                    + "				      </tr>\n"
                    + "				      <tr>\n"
                    + "		            <td class=\"bg_white email-section\">\n"
                    + "		            	<div class=\"heading-section\" style=\"text-align: center; padding: 0 30px;\">\n"
                    + "		              	<h2>Our Blog</h2>\n"
                    + "\n"
                    + "		            	</div>\n"
                    + "		            	<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                    + "		            		<tr>\n"
                    + "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td>\n"
                    + "                              <img src=\"https://i.ibb.co/ncGbb8t/blog-1.jpg\" alt=\"\" style=\"width: 100%; max-width: 600px; height: auto; margin: auto; display: block;\">\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"text-services\" style=\"text-align: left;\">\n"
                    + "                            	<p class=\"meta\"><span>Posted on Feb 18, 2019</span> <span>Food</span></p>\n"
                    + "                            	<h3>Business Key to Success</h3>\n"
                    + "                             	<p>Far far away, behind the word mountains, far from the countries</p>\n"
                    + "                             	<p><a href=\"#\" class=\"btn btn-primary\">Read more</a></p>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td>\n"
                    + "                              <img src=\"https://i.ibb.co/98MJ1Yd/blog-2.jpg\" alt=\"\" style=\"width: 100%; max-width: 600px; height: auto; margin: auto; display: block;\">\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"text-services\" style=\"text-align: left;\">\n"
                    + "                            	<p class=\"meta\"><span>Posted on Feb 18, 2019</span> <span>Food</span></p>\n"
                    + "                            	<h3>Web Design Technique</h3>\n"
                    + "                              <p>Far far away, behind the word mountains, far from the countries</p>\n"
                    + "                              <p><a href=\"#\" class=\"btn btn-primary\">Read more</a></p>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                    </tr>\n"
                    + "		            	</table>\n"
                    + "		            </td>\n"
                    + "		          </tr>\n"
                    + "		          <tr>\n"
                    + "		            <td class=\"bg_light email-section\">\n"
                    + "		            	<div class=\"heading-section\" style=\"text-align: center; padding: 0 30px;\">\n"
                    + "		              	<h2>Our Team</h2>\n"
                    + "		            	</div>\n"
                    + "		            	<table role=\"presentation\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">\n"
                    + "		            		<tr>\n"
                    + "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td>\n"
                    + "                              <img src=\"https://i.ibb.co/zxGqrCZ/monta-479x479.jpg\" alt=\"\" style=\"width: 80px; max-width: 600px; height: 100; margin-bottom: 20px; display: block; border-radius: 50%;\">\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"text-testimony\" style=\"\">\n"
                    + "                            	<h3 class=\"name\">Montassar Hizem</h3>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td>\n"
                    + "                              <img src=\"https://i.ibb.co/FDWw05m/alaa-479x479.jpg\" alt=\"\" style=\"width: 80px; max-width: 600px; height: 10; margin-bottom: 20px; display: block; border-radius: 50%;\">\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"text-testimony\" style=\"\">\n"
                    + "                            	<h3 class=\"name\">Alaa Abdelbaki</h3>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                        \n"
                    + "                    </tr>\n"
                    + "		            	</table>\n"
                    + "                        <table role=\"presentation\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">\n"
                    + "		            		<tr>\n"
                    + "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td>\n"
                    + "                              <img src=\"https://i.ibb.co/J2cRtCy/anis-479x479.jpg\" alt=\"\" style=\"width: 80px; max-width: 600px; height: 100; margin-bottom: 20px; display: block; border-radius: 50%;\">\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"text-testimony\" style=\"\">\n"
                    + "                            	<h3 class=\"name\">Anis Bouaziz</h3>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td>\n"
                    + "                              <img src=\"https://i.ibb.co/Pr81Gmt/siwar-479x479.jpg\" alt=\"\" style=\"width: 80px; max-width: 600px; height: 100; margin-bottom: 20px; display: block; border-radius: 50%;\">\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"text-testimony\" style=\"\">\n"
                    + "                            	<h3 class=\"name\">Siwar Barhoumi</h3>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                        \n"
                    + "                    </tr>\n"
                    + "		            	</table>\n"
                    + "                        <table role=\"presentation\" border=\"0\" cellpadding=\"10\" cellspacing=\"0\" width=\"100%\">\n"
                    + "		            		<tr>\n"
                    + "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td>\n"
                    + "                              <img src=\"https://i.ibb.co/D8qYLyw/hala-479x479.jpg\" alt=\"\" style=\"width: 80px; max-width: 600px; height: 100; margin-bottom: 20px; display: block; border-radius: 50%;\">\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"text-testimony\" style=\"\">\n"
                    + "                            	<h3 class=\"name\">Hela Gheddas</h3>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n"
                    + "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                          <tr>\n"
                    + "                            <td>\n"
                    + "                              <img src=\"https://i.ibb.co/VCjmRfQ/sarah-479x479.jpg\" alt=\"\" style=\"width: 80px; max-width: 600px; height: 100; margin-bottom: 20px; display: block; border-radius: 50%;\">\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                          <tr>\n"
                    + "                            <td class=\"text-testimony\" style=\"\">\n"
                    + "                            	<h3 class=\"name\">Sarah Zribi</h3>\n"
                    + "                            </td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                        \n"
                    + "                    </tr>\n"
                    + "		            	</table>\n"
                    + "		            </td>\n"
                    + "		          </tr>\n"
                    + "		        </table>\n"
                    + "\n"
                    + "		      </td>\n"
                    + "		    </tr>\n"
                    + "\n"
                    + "      </table>\n"
                    + "      <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"margin: auto;\">\n"
                    + "      	<tr>\n"
                    + "          <td valign=\"middle\" class=\"bg_black footer email-section\">\n"
                    + "            <table>\n"
                    + "            	<tr>\n"
                    + "                <td valign=\"top\" width=\"60%\" style=\"padding-top: 20px;\">\n"
                    + "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                    <tr>\n"
                    + "                      <td style=\"text-align: left; padding-right: 10px;\">\n"
                    + "                      	<h3 class=\"heading\">About</h3>\n"
                    + "                      	<p>our application attracts a variety of participants, from across Tunisia   and abroad, to take part and who possess some form of talents, with acts ranging from singing, dancing, comedy, magic, stunts, variety, and other genres</p>\n"
                    + "                      	<ul class=\"social\">\n"
                    + "                      		<li><img src=\"images/002-play-button.png\" alt=\"\" style=\"width: 30px; max-width: 600px; height: auto; display: block;\"></li>\n"
                    + "                      		<li><img src=\"images/002-play-button.png\" alt=\"\" style=\"width: 30px; max-width: 600px; height: auto; display: block;\"></li>\n"
                    + "                      		<li><img src=\"images/002-play-button.png\" alt=\"\" style=\"width: 30px; max-width: 600px; height: auto; display: block;\"></li>\n"
                    + "                      	</ul>\n"
                    + "                      </td>\n"
                    + "                    </tr>\n"
                    + "                  </table>\n"
                    + "                </td>\n"
                    + "                <td valign=\"top\" width=\"40%\" style=\"padding-top: 20px;\">\n"
                    + "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                    <tr>\n"
                    + "                      <td style=\"text-align: left; padding-left: 5px; padding-right: 5px;\">\n"
                    + "                      	<h3 class=\"heading\">Contact Info</h3>\n"
                    + "                      	<ul>\n"
                    + "					                <li><span class=\"text\">2035 Sunset Lake, Residence El Amen,Ariana</span></li>\n"
                    + "                                    <li><span class=\"text\">tunisiagottalents2020@gmail.com</span></li>\n"
                    + "                                    <li><span class=\"text\">+216 55 418 582</span></li>\n"
                    + "					              </ul>\n"
                    + "                      </td>\n"
                    + "                    </tr>\n"
                    + "                  </table>\n"
                    + "                </td>\n"
                    + "              </tr>\n"
                    + "            </table>\n"
                    + "          </td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "        	<td valign=\"middle\" class=\"bg_black footer email-section\">\n"
                    + "        		<table>\n"
                    + "            	<tr>\n"
                    + "                <td valign=\"top\" width=\"33.333%\">\n"
                    + "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                    + "                    <tr>\n"
                    + "                      <td style=\"text-align: left; padding-right: 10px;\">\n"
                    + "                      	<p>&copy; 2020 Tunisia's Got Talent. All Rights Reserved</p>\n"
                    + "                      </td>\n"
                    + "                    </tr>\n"
                    + "                  </table>\n"
                    + "                </td>  \n"
                    + "              </tr>\n"
                    + "            </table>\n"
                    + "        	</td>\n"
                    + "        </tr>\n"
                    + "      </table>\n"
                    + "\n"
                    + "    </div>\n"
                    + "  </center>\n"
                    + "</body>\n"
                    + "</html>", "text/html; charset=utf-8");

        } catch (MessagingException ex) {
            Logger.getLogger(sendEmailSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }

        Transport.send(message);

        System.out.println("Success !!");
    }

}
