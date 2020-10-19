package com.chursinov.beautysalon.util;

import com.chursinov.beautysalon.entity.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {
    public static void SendEmail(User user) throws IOException, MessagingException {
        Properties properties = new Properties();
        properties.load(SendEmail.class.getClassLoader().getResourceAsStream("mail.properties"));

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);

        message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(user.getEmail())});
        message.setSubject(properties.getProperty("mail.subject"));
        message.setText(properties.getProperty("mail.text"));

        Transport transport = mailSession.getTransport();
        transport.connect(properties.getProperty("mail.smtp.authentication.sender.email"), properties.getProperty("mail.smtp.authentication.sender.password"));
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
