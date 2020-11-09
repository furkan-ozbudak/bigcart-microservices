package com.bigcart.productservice.bigcartproductservice.DTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


public class VendorDTO extends PersonDTO {

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    private boolean isApproved;

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
