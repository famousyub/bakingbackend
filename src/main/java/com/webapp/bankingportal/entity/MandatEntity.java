package com.webapp.bankingportal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mandat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MandatEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private BigDecimal numSeqMandat;
    private String codMandat;
    private Long montantMandat;
    private Date dateCreation;
    private String compte;
    private String cinEmeteur;
    private Integer numTelEmetteur;
    private Date dateValidite;
    private String cinRecepteur;
    private String nomRecepteur;
    private String prenomRecepteur;




}
