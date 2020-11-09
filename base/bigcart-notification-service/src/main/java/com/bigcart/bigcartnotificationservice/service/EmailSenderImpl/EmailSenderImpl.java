package com.bigcart.bigcartnotificationservice.service.EmailSenderImpl;

import com.bigcart.bigcartnotificationservice.model.model.Email;
import com.bigcart.bigcartnotificationservice.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    JavaMailSender javaMailSender;
    Email email;
    @Autowired
    EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendEmail(Email email) throws MailException, MessagingException {
        this.email = email;
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setFrom("bigcart.notification.service@gmail.com");
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(
                "Dear " + email.getUserName() + "," +
                        "<br>Greeting from BigCart." +
                        "<br><p>" + email.getBody() + "</p>" +
                        "<br>" +
                        "Thanks,<br> BigCart" +
                        "<br> <hr>bigcart.notification.service@gmail.com</hr>"
                , true);
        this.javaMailSender.send(message.getMimeMessage());
    }
}
