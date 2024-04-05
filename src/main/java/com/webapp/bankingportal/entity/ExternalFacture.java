package com.webapp.bankingportal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="externafacture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;


    private String factname ;


    private double amount;

    private String accountnumber ;

    private Date transaction_date;
}
