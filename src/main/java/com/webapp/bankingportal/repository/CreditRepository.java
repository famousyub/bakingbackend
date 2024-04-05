package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CreditRepository extends JpaRepository<Credit,Long> {

    @Query("select c from Credit c  where c.accountNumber=?1")
    List<Credit> getAllCredits(String accounts );
}
