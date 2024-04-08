package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FactureRepository extends JpaRepository<Facture,Long> {

}
