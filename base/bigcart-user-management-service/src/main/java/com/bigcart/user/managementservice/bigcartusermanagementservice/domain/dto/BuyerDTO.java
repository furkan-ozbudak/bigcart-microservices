package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.dto;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
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
