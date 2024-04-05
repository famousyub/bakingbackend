package com.webapp.bankingportal.payload.request;


import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Set;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;


  private  String name ;

  private String address;
  private String phone_number;
  private int otpRetryCount;
  private LocalDateTime lastOtpRequestTime;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
