package com.bigcart.apigateway.dto;

import java.util.Date;
import java.util.Set;

public class EmployeeDTO extends PersonDTO {

    public EmployeeDTO()
    {

    }

//    public EmployeeDTO(String payrollNumber, boolean isAdmin, double salary, long id, String firstName, String lastName, String email, String phone, Date creationDateTime, Set<Address> addresses, Status status) {
//        //super(id, firstName, lastName, email, phone, creationDateTime, addresses, status);
//        this.payrollNumber = payrollNumber;
//        this.isAdmin = isAdmin;
//        this.salary = salary;
//        this.setId(id);
//        this.setFirstName(firstName);
//        this.setLastName(lastName);
//        this.setEmail(email);
//        this.setPhone(phone);
//        this.setCreationDateTime(creationDateTime);
//        this.setAddresses(addresses);
//        this.setStatus(status);
//
//    }

    private String payrollNumber;

    private boolean isAdmin;

    private double salary;

    public String getPayrollNumber() {
        return payrollNumber;
    }

    public void setPayrollNumber(String payrollNumber) {
        this.payrollNumber = payrollNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }
}
