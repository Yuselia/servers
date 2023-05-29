package com.servers.test;

import java.util.*;

public class Contact {
  private String firstName;
  private String lastName;
  private String middleName;
  private String email;
  private String email2;
  private String phone;
  private Role role;
  private String company;
  private String jobTitle;
  private String jobRole;
  private String nickname;
  private String comments;
  private List<ContactDetails> contactDetails = new ArrayList<>();

  public Contact(String firstName, Role role) {
    this.firstName = firstName;
    this.role = role;
  }

  public static class ContactDetails {
    private String name;
    private String value;

    public ContactDetails(String name, String value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }

  public List<ContactDetails> getContactDetails() {
    return contactDetails;
  }

  public Contact setContactDetails(ContactDetails contactDetails) {
    this.contactDetails.add(contactDetails);
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Contact setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Contact setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getMiddleName() {
    return middleName;
  }

  public Contact setMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Contact setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getSecondaryEmail() {
    return email2;
  }

  public Contact setSecondaryEmail(String email) {
    this.email2 = email;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public Contact setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getRole() {
    return role.name();
  }

  public Contact setRole(Role role) {
    this.role = role;
    return this;
  }

  public String getCompany() {
    return company;
  }

  public Contact setCompany(String company) {
    this.company = company;
    return this;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public Contact setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  public String getJobRole() {
    return jobRole;
  }

  public Contact setJobRole(String jobRole) {
    this.jobRole = jobRole;
    return this;
  }

  public String getNickname() {
    return nickname;
  }

  public Contact setNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public String getComments() {
    return comments;
  }

  public Contact setComments(String comments) {
    this.comments = comments;
    return this;
  }
}
