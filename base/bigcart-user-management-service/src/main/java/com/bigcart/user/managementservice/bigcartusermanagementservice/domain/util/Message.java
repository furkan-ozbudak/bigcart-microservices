package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.util;

public class Message {

    private String userName;

    private String subject;

    private String body;

    private String to;

    public String getUserName() {
        return userName;
    }

    private Message(){};

    public Message(String userName, String subject, String body, String toAddress)
    {
        this.userName = userName;
        this.body = body;
        this.subject = subject;
        this.to = toAddress;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String toAddress) {
        this.to = toAddress;
    }
}
