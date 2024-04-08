package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompteEpargneRepository extends JpaRepository<CompteEpargne,Long> {
}
