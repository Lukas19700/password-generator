package com.example.passwordgenerator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passwords")
public class PasswordEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer length;
  private Boolean hasUpperCase;
  private Boolean hasNumbers;
  private Boolean hasSymbols;
  private String password;

  public PasswordEntity(Integer length, Boolean hasUpperCase, Boolean hasNumbers,
    Boolean hasSymbols,String password) {
    this.length = length;
    this.hasUpperCase = hasUpperCase;
    this.hasNumbers = hasNumbers;
    this.hasSymbols = hasSymbols;
    this.password = password;
  }

  public PasswordEntity() {

  }

  public Long getID() {
    return id;
  }

  public void setID(Long ID) {
    this.id = id;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Boolean getHasUpperCase() {
    return hasUpperCase;
  }

  public void setHasUpperCase(Boolean hasUpperCase) {
    this.hasUpperCase = hasUpperCase;
  }

  public Boolean getHasNumbers() {
    return hasNumbers;
  }

  public void setHasNumbers(Boolean hasNumbers) {
    this.hasNumbers = hasNumbers;
  }

  public Boolean getHasSymbols() {
    return hasSymbols;
  }

  public void setHasSymbols(Boolean hasSymbols) {
    this.hasSymbols = hasSymbols;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
