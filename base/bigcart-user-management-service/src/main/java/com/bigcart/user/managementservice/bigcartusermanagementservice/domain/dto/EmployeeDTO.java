package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.dto;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

public class EmployeeDTO extends PersonDTO {

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
