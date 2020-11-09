package com.bigcart.bigcartnotificationservice.service;

import com.bigcart.bigcartnotificationservice.model.model.Email;


import javax.mail.MessagingException;

public interface EmailSender {
    public void sendEmail(Email email) throws MessagingException;
}
