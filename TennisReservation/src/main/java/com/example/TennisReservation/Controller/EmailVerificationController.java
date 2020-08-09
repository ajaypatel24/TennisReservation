package com.example.TennisReservation.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailVerificationController extends AppController {

    @RequestMapping("/SendEmail/")
    public void sendMail() {
        final String username = "ajaypatel24@hotmail.com";
        final String password = this.sendPass();

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("ajaypatel24@hotmail.com"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("a-tel@live.ca"));
            message.setSubject("Test");
            message.setText("HI");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/VerificationEmail/")
    public void readEmailConfirmation() throws IOException {
        final String username = "ajaypatel24@hotmail.com";
        final String password = this.sendPass();
        final String host = "smtp-mail.outlook.com";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Store store = session.getStore("imaps"); // very important, the configured props are done for imaps, pop3  will not work

            store.connect(host, username, password);

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            SearchTerm sender = new FromTerm(new InternetAddress("NePasRepondre@montreal.ca"));
            SearchTerm unread = new FlagTerm(new Flags(Flags.Flag.SEEN), true);
            SearchTerm[] filters = { sender, unread };
            SearchTerm search = new AndTerm(filters);
            Message[] messages = folder.search(search);

            for (int i = 1; i < messages.length; i++) {
                System.out.println(messages[i].getSubject());
                getAttachment(messages[i]);
            }

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAttachment(Message message) {
        try {
            String AttachFile = "";
            String MessageContent = "";
            Multipart multipart = (Multipart) message.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                MimeBodyPart bodyPart = (MimeBodyPart) multipart.getBodyPart(i);

                if (bodyPart.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                    String filename = bodyPart.getFileName();
                    AttachFile += filename + ", ";
                    bodyPart.saveFile("/Users/ajaypatel/Desktop/TennisReservation/TennisReservation/src/confirmation"
                            + File.separator + filename);
                } else {
                    MessageContent = bodyPart.getContent().toString();
                }

            }
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

}
