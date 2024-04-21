package com.webapp.bankingportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "accountno")
    public BigInteger accountNo;

    @Column(name = "saccountno")
    public BigInteger sendAccountNo;

    @Column(name = "raccountno")
    public BigInteger receiveAccountNo;

    @Column(name = "amount")
    public BigInteger amount;

    @Column(name = "receivedate")
    public Date receiveDate;

    @Column(name = "status")
    public int status;
}
