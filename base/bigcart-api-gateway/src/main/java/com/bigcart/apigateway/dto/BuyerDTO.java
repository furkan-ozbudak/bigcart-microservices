package com.bigcart.apigateway.dto;

import java.util.Date;

public class BuyerDTO extends PersonDTO {

    private Date dateOfBirth;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
