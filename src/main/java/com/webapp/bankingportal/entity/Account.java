package com.webapp.bankingportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Account implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String accountNumber;
	private double balance;
	private String account_type = "Saving";
	private String branch = "NIT";
	private String IFSC_code = "NIT001";
	private String Pin;

	public BankAccType getBankAccType() {
		return bankAccType;
	}

	public void setBankAccType(BankAccType bankAccType) {
		this.bankAccType = bankAccType;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Set<ExchangeCurrency> getExchanges() {
		return exchanges;
	}

	public void setExchanges(Set<ExchangeCurrency> exchanges) {
		this.exchanges = exchanges;
	}

	private String accountstatus;

    @JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bank_account_type_id", nullable = false)
	private BankAccType bankAccType;

	@Column(name="removed",precision = 0)
	private boolean removed;


	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	private User user;

	@OneToMany(mappedBy = "bankAccount", fetch = FetchType.EAGER)
	private Set<ExchangeCurrency> exchanges;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIFSC_code() {
		return IFSC_code;
	}

	public void setIFSC_code(String iFSC_code) {
		IFSC_code = iFSC_code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + ", account_type="
				+ account_type + ", branch=" + branch + ", IFSC_code=" + IFSC_code + ", user=" + user + "]";
	}

	public String getPin() {
		return Pin;
	}

	public void setPin(String pin) {
		this.Pin = pin;
	}

	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}

}
