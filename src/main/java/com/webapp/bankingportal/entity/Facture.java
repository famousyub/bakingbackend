package com.webapp.bankingportal.entity;




import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "myvfacture")
@Data
@Entity

@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class Facture {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    private Long id;

    private String username ;
    private String accountNumber ;


    private String nom;

    private String prenom;

    private double amount  ;

    private String factname ;

    private String Niveau;


    private String photo;

    @ManyToOne
    @JoinColumn(name = "id_History")
    private FactureHistory factureHistory;



}
