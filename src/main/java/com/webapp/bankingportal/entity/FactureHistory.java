package com.webapp.bankingportal.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

import java.util.List;


@Table(name="mylfacture_history")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class FactureHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_History")
    private Long id;


    private String nom;
    private String prenom;
    private String accountNumber ;

    private String photo;


    @JsonIgnore
    @OneToMany(mappedBy = "factureHistory",fetch = FetchType.LAZY)
    private List<Facture> facture;
}
