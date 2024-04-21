package com.webapp.bankingportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "send")
public class Send {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "accountno")
    public String accountNo;

    @Column(name = "saccountno")
    public String sendAccountNo;

    @Column(name = "raccountno")
    public String  receiveAccountNo;

    @Column(name = "amount")
    public BigInteger amount;

    @Column(name = "sendingdate")
    public Date sendingDate;

    @Column(name = "status")
    public int status;
}
