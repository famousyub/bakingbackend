package com.webapp.bankingportal.payload.request;

import java.math.BigDecimal;
import java.util.Date;

public class MandatMinuteRequest {


    private BigDecimal numSeqMandat;
    private String codMandat; //automatically
    private Long montantMandat;

    private Date dateCreation;


    private String cinEmeteur;
    //private String structureInit;
//	private Integer etatEnvSms;
    private Integer numTelEmetteur;
    //	private Integer nbrTentative;

    private String cinRecepteur;
    private String nomRecepteur;
    private String prenomRecepteur;



    private String usercin ;



}
