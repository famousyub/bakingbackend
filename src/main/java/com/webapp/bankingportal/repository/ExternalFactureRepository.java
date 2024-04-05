package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.ExternalFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExternalFactureRepository extends JpaRepository<ExternalFacture , Long> {


    @Query("select t from   ExternalFacture t where accountnumber=?1 ")
    Optional<ExternalFacture> loadExternalFacture (String accountnumber);
}
