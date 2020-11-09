package com.bigcart.apigateway.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


public class PersonDTO implements Serializable {
  private long id;

  private String firstName;

  private String lastName;

  private String email;

  private String phone;

  private Date creationDateTime;



  private Status status;

  public PersonDTO()
  {

  }

  public PersonDTO(long id, String firstName, String lastName, String email, String phone, Date creationDateTime, Status status) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.creationDateTime = creationDateTime;
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Date getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(Date creationDateTime) {
    this.creationDateTime = creationDateTime;
  }

  public Status getStatus() { return status; }

  public void setStatus(Status status) { this.status = status; }
}