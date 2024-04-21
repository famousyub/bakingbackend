package com.webapp.bankingportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class SavingsAccount {

    // define the necessary fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SavingsTransaction> savingsTransactionsList;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String  getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String  accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<SavingsTransaction> getSavingsTransactionsList() {
        return savingsTransactionsList;
    }

    public void setSavingsTransactionsList(List<SavingsTransaction> savingsTransactionsList) {
        this.savingsTransactionsList = savingsTransactionsList;
    }
}
