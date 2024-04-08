package com.webapp.bankingportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "compte_epargne")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteEpargne extends  AuditModel{

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    private double taux ;

    private String accountNumber ;

    private double solde ;
}
