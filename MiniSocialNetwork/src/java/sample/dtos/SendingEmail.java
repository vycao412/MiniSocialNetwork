/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
public class SendingEmail implements Serializable {

    private static final Logger LOG = Logger.getLogger(SendingEmail.class);

    private String email;
    private String code;

    public SendingEmail(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public void sendEmail() {
        LOG.info("SendingEmail");

        String mail = "sunmoon2092@gmail.com";
        String pass = "Soon@2092";
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Study Viral");
            message.setText("Verification Link: ");
            message.setText("Your Verification Code: " + code);
            Transport.send(message, message.getAllRecipients());
        } catch (Exception e) {
            LOG.error("Error at SendingEmail: " + e.toString());
        }
    }

}
