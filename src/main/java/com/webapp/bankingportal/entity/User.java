package com.webapp.bankingportal.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;

	public String getUsername() {
		return username;
	}

	public User( String username, String email,String password) {
		this.password = password;
		this.username = username;
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@NotBlank
	@Size(max = 20)
	private String username;

	@Column(unique = true)
	private String email;
	private String address;
	private String phone_number;
	private int otpRetryCount;
	private LocalDateTime lastOtpRequestTime;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(  name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	// Establishing a one-to-one relationship with the account
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Account account;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Account getAccount() {
		return account;
	}

	// Convenience method to set the user's account
	public void setAccount(Account account) {
		this.account = account;
		account.setUser(this);
	}

	public int getOtpRetryCount() {
		return otpRetryCount;
	}

	public void setOtpRetryCount(int otpRetryCount) {
		this.otpRetryCount = otpRetryCount;
	}

	public LocalDateTime getLastOtpRequestTime() {
		return lastOtpRequestTime;
	}

	public void setLastOtpRequestTime(LocalDateTime lastOtpRequestTime) {
		this.lastOtpRequestTime = lastOtpRequestTime;
	}

}
