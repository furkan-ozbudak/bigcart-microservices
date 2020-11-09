package com.bigcart.bigcartnotificationservice.controller;

import com.bigcart.bigcartnotificationservice.model.model.Email;
import com.bigcart.bigcartnotificationservice.service.EmailSenderImpl.EmailSenderImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/notify")
public class EmailController {
    @Autowired
    EmailSenderImpl emailSender;

    @PostMapping("/")
    public ResponseEntity<Email> notify(@RequestBody Email email) throws MessagingException {
       emailSender.sendEmail(email);
       return  new ResponseEntity<Email>(email, HttpStatus.OK);
    }
}
