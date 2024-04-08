package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.CompteCourant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompteCourantRepository extends JpaRepository<CompteCourant,Long> {
}
