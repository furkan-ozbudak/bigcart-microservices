package com.bigcart.productservice.bigcartproductservice.DTO;

public class MsgDTO {

    String subject;
    String body;

    public MsgDTO(String subject, String body)
    {
        this.subject = subject;
        this.body = body;
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
}
