package org.example.model.service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

import org.springframework.core.io.ClassPathResource;

@Service
public class EmailSender {
    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String recipientEmail, String firstName, String lastName) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setFrom("ivanchuk9997@yahoo.com", "University Administration");
        helper.setTo(recipientEmail);

        helper.setSubject("Welcome to University of Kathirina!");

        String content = "<html><body style='font-family: Papyrus; font-size: 20px;'>"
                + "<h2>Dear " + firstName + " " + lastName + ",</h2>"
                + "<p>Welcome to University of Kathirina! I wish you easy studying and creative success! &#x1F60A;</p>"
                + "<p>In the attached file below you will find the letter.&#128071;</p>"
                + "<p>Enjoy! &#x2764;</p>"
                + "<p>Best regards!Kathirina &#128051;</p>"
                + "<br><br>"
                + "<p>This email is an automatic mailing and you do not need to reply to it.</p>"
                + "</body></html>";
        helper.setText(content, true);

        // Загрузка изображения как ресурс
        ClassPathResource imageResource = new ClassPathResource("/public/images/letters.jpeg");

        // Добавление изображения как встроенного ресурса (inline)
        helper.addInline("imageKey", imageResource); // "imageKey" - это уникальный ключ для ссылки на изображение в HTML-контенте


        mailSender.send(message);
    }
}