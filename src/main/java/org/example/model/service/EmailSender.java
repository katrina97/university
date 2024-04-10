package org.example.model.service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailSender {
    private JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String recipientEmail, String firstName, String lastName) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("ivanchuk9997@yahoo.com", "University Administration");
        helper.setTo(recipientEmail);

        helper.setSubject("Welcome to University of Kathirina!");

        String content = "Dear " + firstName + " " + lastName + ",\n\n"
                + "Welcome to University of Kathirina! I wish you easy studying and creative success!\n\n"
                + "Enjoy!\n\n"
                + "This email is an automatic mailing and you do not need to reply to it.";
        helper.setText(content);

        mailSender.send(message);
    }
}