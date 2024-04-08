package com.webapp.bankingportal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Table(name = "compte_courant")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteCourant extends  AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    private double decouvert ;

    private String accountNumber ;
}
