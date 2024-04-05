package com.webapp.bankingportal.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="mandatminute")
@Data @AllArgsConstructor @NoArgsConstructor
public class MandatMinute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private BigDecimal numSeqMandat;
    private String codMandat; //automatically
    private Long montantMandat;
    //private String etatMandat;   minute
    private Date dateCreation;
    //	private String sourceCreationMandat; // SMILE, EBANKING, mBANKING
    // private String typeMoyPay;
    private String compte;  ///  banka  cpmpte courant epargne
    private String cinEmeteur;
    //private String structureInit;
//	private Integer etatEnvSms;
    private Integer numTelEmetteur;
    //	private Integer nbrTentative;
    private Date dateValidite;
    private String cinRecepteur;
    private String nomRecepteur;
    private String prenomRecepteur;


    private String  codegenerateur ;



    private String username ;
    private String usercin ;
}
